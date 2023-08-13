package com.example.infotrade.domain.model

import com.squareup.moshi.Json

data class BalanceSheetReport(
    val cashAndShortTermInvestments: String,
    val totalAssets: String,
    val totalLiabilities: String,
    val totalEquity: String,
    val shareOutstanding: String,
    val date: String
)
