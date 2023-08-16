package com.example.infotrade.presentation.company_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.infotrade.presentation.company_info.companyviewmodel.CompanyInfoViewModel
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Destination
fun CompanyInfoScreen(
    symbol: String,
    viewModel: CompanyInfoViewModel = hiltViewModel()
) {
    val state = viewModel.companyInfoState

    if (state.error == null) {
        state.company?.let { company ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                company.symbol,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        },
                        backgroundColor = Color.Blue,
                        contentColor = Color.White,
                        elevation = AppBarDefaults.TopAppBarElevation
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    if (state.stockInfos.isNotEmpty()) {
                        val currentClose = state.stockInfos[state.stockInfos.size - 1].close.toFloat()
                        val previousClose = state.stockInfos[0].close.toFloat()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            Arrangement.Start,
                            Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$${currentClose}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Chip(
                                onClick = { /* Do something! */ },
                                colors = ChipDefaults.chipColors(
                                ),
                                leadingIcon = {
                                    if (currentClose - previousClose < 0) {
                                        Icon(
                                            Icons.Default.ArrowDownward,
                                            contentDescription = "Change",
                                            tint = Color.Red
                                        )
                                    }else {
                                        Icon(
                                            Icons.Default.ArrowUpward,
                                            contentDescription = "Change",
                                            tint = Color.Green
                                        )
                                    }

                                }
                            ) {
                                if (currentClose-previousClose < 0) {
                                    Text(
                                        text = viewModel.calculatePerChange(
                                            currentClose = currentClose,
                                            previousClose = previousClose
                                        ),
                                        fontSize = 20.sp,
                                        color = Color.Red
                                    )
                                }else {
                                    Text(
                                        text = viewModel.calculatePerChange(
                                            currentClose = currentClose,
                                            previousClose = previousClose
                                        ),
                                        fontSize = 20.sp,
                                        color = Color.Green
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(6.dp))
                            val amountChange = currentClose-previousClose

                            if (currentClose-previousClose < 0) {
                                Text(
                                    text = String.format("%.2f", amountChange),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.Red
                                )
                            }else {
                                Text(
                                    text = String.format("%.2f", amountChange),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.Green
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Market Summery")
                        Spacer(modifier = Modifier.height(20.dp))

                        if (previousClose <= currentClose) {
                            StockChart(
                                infos = state.stockInfos,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .align(CenterHorizontally),
                                graphColor = Color.Green
                            )

                        } else {
                            StockChart(
                                infos = state.stockInfos,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .align(CenterHorizontally),
                                graphColor = Color.Red
                            )
                        }
                        Spacer(modifier = Modifier.height(25.dp))

                    }
                    Divider(modifier = Modifier.fillMaxWidth())

                    if (state.stockInfos.isEmpty() || state.incomeStatementInfo?.annualReports?.isEmpty() == true || state.balanceSheetInfo?.quarterReport?.isEmpty() == true || state.cashFlowInfo?.quarterReport?.isEmpty() == true) {

                    } else {

                        val currentClose = state.stockInfos[state.stockInfos.size - 1].close.toFloat()
                        val previousClose = state.stockInfos[0].close.toFloat()


                        StockHighlights(
                            previousClose = "$$previousClose",
                            dayRange = "$$previousClose - $$currentClose",
                            marketCap = viewModel.getFormatedNumber(state.company.marketCap.toLong()) + " USD",
                            peRatio = state.company.peRatio,
                            dividendYield = (state.company.dividendYield.toFloat() * 100).toString() + "%",
                            primaryExchange = state.company.primaryExchange
                        )

                        // Income Statement
                        var isIncomeStatementVisible by remember { mutableStateOf(true) }

                        state.incomeStatementInfo?.quarterlyReports?.get(0)?.let { incomeStatement ->
                                val year = viewModel.getYear(incomeStatement.date)
                                val quarter = viewModel.getMonthYear(incomeStatement.date)

                                IncomeStatement(
                                    revenue = viewModel.getFormatedNumber(incomeStatement.revenue.toLong()),
                                    revenueYnYChange = viewModel.calculatePerChangeLong(
                                        state.incomeStatementInfo.quarterlyReports[0].revenue.toFloat(),
                                        state.incomeStatementInfo.quarterlyReports[1].revenue.toFloat()),
                                    operatingExpense = viewModel.getFormatedNumber(incomeStatement.operatingExpense.toLong()),
                                    operatingExpenseYnYChange = viewModel.calculatePerChangeLong(
                                        state.incomeStatementInfo.quarterlyReports[0].operatingExpense.toFloat(),
                                        state.incomeStatementInfo.quarterlyReports[1].operatingExpense.toFloat()),
                                    netIncome = viewModel.getFormatedNumber(incomeStatement.netIncome.toLong()),
                                    netIncomeYnYChange = viewModel.calculatePerChangeLong(
                                        state.incomeStatementInfo.quarterlyReports[0].netIncome.toFloat(),
                                        state.incomeStatementInfo.quarterlyReports[1].netIncome.toFloat()),
                                    ebitda = viewModel.getFormatedNumber(incomeStatement.editda.toLong()),
                                    ebitdaYnYChange = viewModel.calculatePerChangeLong(
                                        state.incomeStatementInfo.quarterlyReports[0].editda.toFloat(),
                                        state.incomeStatementInfo.quarterlyReports[1].editda.toFloat()),
                                    PreviousYear = quarter,
                                    isVisible = isIncomeStatementVisible
                                ) {
                                    isIncomeStatementVisible = !isIncomeStatementVisible
                                }
                            }

                        Divider(modifier = Modifier.fillMaxWidth())

                        // Balance Sheet
                        var isBalanceSheetVisible by remember { mutableStateOf(false) }

                        state.balanceSheetInfo?.quarterReport?.get(0)?.let { balanceSheetReport ->
                            val year = viewModel.getYear(balanceSheetReport.date)
                            val quarter = viewModel.getMonthYear(balanceSheetReport.date)

                            BalanceSheet(
                                cashAndShortTermInvestment = viewModel.getFormatedNumber(
                                    balanceSheetReport.cashAndShortTermInvestments.toLong()
                                ),
                                cashAndShortTermInvestmentYnYChange = "23B",
                                totalAssets = viewModel.getFormatedNumber(balanceSheetReport.totalAssets.toLong()),
                                totalAssetsYnYChange = viewModel.calculatePerChangeLong(
                                    state.balanceSheetInfo.quarterReport[0].totalAssets.toFloat(),
                                    state.balanceSheetInfo.quarterReport[1].totalAssets.toFloat()
                                ),
                                totalLiabilities = viewModel.getFormatedNumber(balanceSheetReport.totalLiabilities.toLong()),
                                totalLiabilitiesYnYChange = viewModel.calculatePerChangeLong(
                                    state.balanceSheetInfo.quarterReport[0].totalLiabilities.toFloat(),
                                    state.balanceSheetInfo.quarterReport[1].totalLiabilities.toFloat()
                                ),
                                totalEquity = viewModel.getFormatedNumber(balanceSheetReport.totalEquity.toLong()),
                                totalEquityYnYChange = viewModel.calculatePerChangeLong(
                                    state.balanceSheetInfo.quarterReport[0].totalEquity.toFloat(),
                                    state.balanceSheetInfo.quarterReport[1].totalEquity.toFloat()
                                ),
                                sharesOutstanding = viewModel.getFormatedNumber(balanceSheetReport.shareOutstanding.toLong()),
                                sharesOutstandingYnYChange = viewModel.calculatePerChangeLong(
                                    state.balanceSheetInfo.quarterReport[0].shareOutstanding.toFloat(),
                                    state.balanceSheetInfo.quarterReport[1].shareOutstanding.toFloat()
                                ),
                                PreviousYear = quarter,
                                isVisible = isBalanceSheetVisible
                            ) {
                                isBalanceSheetVisible = !isBalanceSheetVisible
                            }

                        }


                        Divider(modifier = Modifier.fillMaxWidth())

                        // Cash Flow
                        var isCashFlowVisible by remember { mutableStateOf(false) }

                        state.cashFlowInfo?.quarterReport?.get(0)?.let { cashFlowReport ->
                            val year = viewModel.getYear(cashFlowReport.date)
                            val quarter = viewModel.getMonthYear(cashFlowReport.date)

                            CashFlow(
                                netIncome = viewModel.getFormatedNumber(cashFlowReport.netIncome.toLong()),
                                netIncomeYnYChange = viewModel.calculatePerChangeLong(
                                    state.cashFlowInfo.quarterReport[0].netIncome.toFloat(),
                                    state.cashFlowInfo.quarterReport[1].netIncome.toFloat()
                                ),
                                cashForOperations = viewModel.getFormatedNumber(cashFlowReport.cashForOperations.toLong()),
                                cashForOperationsYnYChange = viewModel.calculatePerChangeLong(
                                    state.cashFlowInfo.quarterReport[0].cashForOperations.toFloat(),
                                    state.cashFlowInfo.quarterReport[1].cashForOperations.toFloat()
                                ),
                                cashForInvesting = viewModel.getFormatedNumber(cashFlowReport.cashForInvesting.toLong()),
                                cashForInvestingYnYChange = viewModel.calculatePerChangeLong(
                                    state.cashFlowInfo.quarterReport[0].cashForInvesting.toFloat(),
                                    state.cashFlowInfo.quarterReport[1].cashForInvesting.toFloat()
                                ),
                                cashForFinancing = viewModel.getFormatedNumber(cashFlowReport.cashForFinancing.toLong()),
                                cashForFinancingYnYChange = viewModel.calculatePerChangeLong(
                                    state.cashFlowInfo.quarterReport[0].cashForFinancing.toFloat(),
                                    state.cashFlowInfo.quarterReport[1].cashForFinancing.toFloat()
                                ),
                                PreviousYear = quarter,
                                isVisible = isCashFlowVisible
                            ) {
                                isCashFlowVisible = !isCashFlowVisible
                            }

                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Industry: ${company.industry}",
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Country: ${company.country}",
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = company.description,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {

            }
        }
    }

}