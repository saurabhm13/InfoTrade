package com.example.InfoTrade.domain.repository

import com.example.InfoTrade.domain.model.CompanyInfo
import com.example.InfoTrade.domain.model.CompanyListing
import com.example.InfoTrade.domain.model.IntradayInfo
import com.example.InfoTrade.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>

}