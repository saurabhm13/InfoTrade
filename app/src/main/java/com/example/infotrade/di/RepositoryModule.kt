package com.example.infotrade.di

import com.example.infotrade.data.csv.CSVParser
import com.example.infotrade.data.csv.CompanyListingParser
import com.example.infotrade.data.csv.IntradayInfoParser
import com.example.infotrade.data.repository.StockRepositoryImpl
import com.example.infotrade.domain.model.CompanyListing
import com.example.infotrade.domain.model.IntradayInfo
import com.example.infotrade.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

}