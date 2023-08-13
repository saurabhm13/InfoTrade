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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            Arrangement.Start,
                            Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$" + state.stockInfos[state.stockInfos.size - 1].close.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Chip(
                                onClick = { /* Do something! */ },
                                colors = ChipDefaults.chipColors(
                                    backgroundColor = Color.White,
                                    contentColor = Color.Red
                                )
                            ) {
                                Text(
                                    text = "3.45%",
                                    fontSize = 20.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "1.34",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Market Summery")
                        Spacer(modifier = Modifier.height(20.dp))

                        if (state.stockInfos[0].close <= state.stockInfos[state.stockInfos.size - 1].close) {
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

                        // Stock Highlights
                        StockHighlights(
                            previousClose = "$" + state.stockInfos[0].close.toString(),
                            dayRange = "$" + state.stockInfos[0].close.toString() + " - $" + state.stockInfos[state.stockInfos.size - 1].close.toString(),
                            marketCap = viewModel.getFormatedNumber(state.company.marketCap.toLong()) + " USD",
                            peRatio = state.company.peRatio,
                            dividendYield = (state.company.dividendYield.toInt()*100).toString()+"%",
                            primaryExchange = state.company.primaryExchange
                        )

                    }
                    Divider(modifier = Modifier.fillMaxWidth())

                    // Income Statement
                    var isIncomeStatementVisible by remember { mutableStateOf(true) }


                    state.incomeStatementInfo?.quarterlyReports?.get(0)?.let { incomeStatement ->
                        val year = viewModel.getYear(incomeStatement.date)
                        val quarter = viewModel.getMonthYear(incomeStatement.date)

                        IncomeStatement(
                            revenue = viewModel.getFormatedNumber(incomeStatement.revenue.toLong()),
                            revenueYnYChange = "10%",
                            operatingExpense = viewModel.getFormatedNumber(incomeStatement.operatingExpense.toLong()),
                            operatingExpenseYnYChange = "1B",
                            netIncome = viewModel.getFormatedNumber(incomeStatement.netIncome.toLong()),
                            netIncomeYnYChange = "3.4%",
                            ebitda = viewModel.getFormatedNumber(incomeStatement.editda.toLong()),
                            ebitdaYnYChange = "5.53%",
                            PreviousYear = quarter,
                            isVisible = isIncomeStatementVisible
                        ) {
                            isIncomeStatementVisible = !isIncomeStatementVisible
                        }
                    }
                    Divider(modifier = Modifier.fillMaxWidth())

                    // Balance Sheet
                    var isBalanceSheetVisible by remember { mutableStateOf(false) }

                    BalanceSheet(
                        cashAndShortTermInvestment = "19B",
                        cashAndShortTermInvestmentYnYChange = "23B",
                        totalAssets = "45B",
                        totalAssetsYnYChange = "3%",
                        totalLiabilities = "10B",
                        totalLiabilitiesYnYChange = "1.3%",
                        totalEquity = "65B",
                        totalEquityYnYChange = "2.5%",
                        sharesOutstanding = "1B",
                        sharesOutstandingYnYChange = "6%",
                        PreviousYear = "2022",
                        isVisible = isBalanceSheetVisible
                    ) {
                        isBalanceSheetVisible = !isBalanceSheetVisible
                    }
                    Divider(modifier = Modifier.fillMaxWidth())

                    // Cash Flow
                    var isCashFlowVisible by remember { mutableStateOf(false) }

                    CashFlow(
                        netIncome = "18B",
                        netIncomeYnYChange = "3.45%",
                        cashForOperations = "12B",
                        cashForOperationsYnYChange = "2%",
                        cashForInvesting = "34B",
                        cashForInvestingYnYChange = "5.2%",
                        cashForFinancing = "23B",
                        cashForFinancingYnYChange = "1%",
                        PreviousYear = "2022",
                        isVisible = isCashFlowVisible
                    ) {
                        isCashFlowVisible = !isCashFlowVisible
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
                    if (state.stockInfos.isNotEmpty()) {


                    }

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