package com.example.infotrade.domain.model

data class IncomeStatement(
    val annualReports: List<IncomeStatementReport>,
    val quarterlyReports: List<IncomeStatementReport>,
    val symbol: String
)