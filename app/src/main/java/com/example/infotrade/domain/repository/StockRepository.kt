package com.example.infotrade.domain.repository

import com.example.infotrade.domain.model.BalanceSheet
import com.example.infotrade.domain.model.CashFlow
import com.example.infotrade.domain.model.CompanyInfo
import com.example.infotrade.domain.model.CompanyListing
import com.example.infotrade.domain.model.IncomeStatement
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

    suspend fun getIncomeStatementInfo(
        symbol: String
    ): Resource<IncomeStatement>

    suspend fun getBalanceSheet(
        symbol: String
    ): Resource<BalanceSheet>

    suspend fun getCashFlow(
        symbol: String
    ): Resource<CashFlow>

}