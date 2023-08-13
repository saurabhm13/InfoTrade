package com.example.infotrade.domain.model

data class BalanceSheet(
    val annualReport: List<BalanceSheetReport>,
    val quarterReport: List<BalanceSheetReport>,
    val symbol: String
)
