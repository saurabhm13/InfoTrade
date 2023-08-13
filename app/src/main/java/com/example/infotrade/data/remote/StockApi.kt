package com.example.infotrade.data.remote

import com.example.infotrade.data.remote.dto.CompanyInfoDto
import com.example.infotrade.data.remote.dto.IncomeStatementDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListing(
        @Query("apikey") apiKey: String = API_KEY1
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY1
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY1
    ): CompanyInfoDto

    @GET("query?function=INCOME_STATEMENT")
    suspend fun getIncomeStatement(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY2
    ): IncomeStatementDto

    @GET("query?function=BALANCE_SHEET")
    suspend fun getBalanceSheet(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY2
    )

    companion object {
        const val API_KEY1 = "IK0WBHK9RZSL85SK"
        const val API_KEY2 = "RUQJGO2M8O0ECDGJ"
        const val API_KEY3 = "359LNYQEWO4RND3Z"
        const val BASE_URL = "https://alphavantage.co"
    }

}