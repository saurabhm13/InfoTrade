package com.example.InfoTrade.di

import com.example.InfoTrade.data.csv.CSVParser
import com.example.InfoTrade.data.csv.CompanyListingParser
import com.example.InfoTrade.data.csv.IntradayInfoParser
import com.example.InfoTrade.data.repository.StockRepositoryImpl
import com.example.InfoTrade.domain.model.CompanyListing
import com.example.InfoTrade.domain.model.IntradayInfo
import com.example.InfoTrade.domain.repository.StockRepository
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