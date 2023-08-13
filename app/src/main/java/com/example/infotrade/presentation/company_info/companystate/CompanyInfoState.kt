package com.example.infotrade.presentation.company_info.companystate

import com.example.infotrade.domain.model.BalanceSheet
import com.example.infotrade.domain.model.CashFlow
import com.example.infotrade.domain.model.CompanyInfo
import com.example.infotrade.domain.model.IncomeStatement
import com.example.infotrade.domain.model.IncomeStatementReport
import com.example.infotrade.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val incomeStatementInfo: IncomeStatement? = null,
    val balanceSheetInfo: BalanceSheet? = null,
    val cashFlowInfo: CashFlow? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
