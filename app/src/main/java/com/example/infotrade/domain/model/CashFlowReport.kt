package com.example.infotrade.domain.model

import com.squareup.moshi.Json

data class CashFlowReport(
    val netIncome: String,
    val cashForOperations: String,
    val cashForInvesting: String,
    val cashForFinancing: String,
    val date: String
)
