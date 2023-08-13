package com.example.infotrade.data.mappers

import com.example.infotrade.data.local.CompanyListingEntity
import com.example.infotrade.data.remote.dto.CompanyInfoDto
import com.example.infotrade.data.remote.dto.IncomeStatementDto
import com.example.infotrade.data.remote.dto.IncomeStatementReportDto
import com.example.infotrade.domain.model.CompanyInfo
import com.example.infotrade.domain.model.CompanyListing
import com.example.infotrade.domain.model.IncomeStatement
import com.example.infotrade.domain.model.IncomeStatementReport

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol?: "",
        description = description?: "",
        name = name?: "",
        country = country?: "",
        industry = industry?: "",
        marketCap = marketCap?: "",
        peRatio = peRatio?: "",
        dividendYield = dividendYield?: "",
        primaryExchange = exchange?: ""
    )
}

fun IncomeStatementReportDto.toIncomeStatementReport(): IncomeStatementReport {
    return IncomeStatementReport(
        revenue = revenue?: "",
        operatingExpense = operatingExpenses?: "",
        netIncome = netIncome?: "",
        editda = editda?: "",
        date = date?: ""
    )
}

fun IncomeStatementDto.toIncomeStatement(): IncomeStatement {
    return IncomeStatement(
        annualReports = annualReports.map { it.toIncomeStatementReport() },
        quarterlyReports = quarterlyReports.map { it.toIncomeStatementReport() },
        symbol = symbol
    )
}