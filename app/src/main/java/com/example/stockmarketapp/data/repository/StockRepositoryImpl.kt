package com.example.stockmarketapp.data.repository

import com.example.stockmarketapp.data.csv.CSVParser
import com.example.stockmarketapp.data.local.StockDatabase
import com.example.stockmarketapp.data.mappers.toCompanyListing
import com.example.stockmarketapp.data.mappers.toCompanyListingEntity
import com.example.stockmarketapp.data.remote.StockApi
import com.example.stockmarketapp.domain.model.CompanyListing
import com.example.stockmarketapp.domain.repository.StockRepository
import com.example.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListing.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListing = try {
                val response = api.getListing()
                companyListingParser.parse(response.byteStream())
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let { listing ->
                dao.clearCompanyListing()
                dao.insertCompanyListing(
                    listing.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("").map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }


}