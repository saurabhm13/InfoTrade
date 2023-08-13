package com.example.infotrade.domain.model

data class CashFlow(
    val annualReport: List<CashFlowReport>,
    val quarterReport: List<CashFlowReport>,
    val symbol: String
)
