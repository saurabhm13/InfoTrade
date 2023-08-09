package com.example.infotrade.domain.repository

import com.example.infotrade.domain.model.CompanyInfo
import com.example.infotrade.domain.model.CompanyListing
import com.example.infotrade.domain.model.IntradayInfo
import com.example.infotrade.util.Resource
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