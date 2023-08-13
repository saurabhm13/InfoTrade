package com.example.infotrade.presentation.company_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.infotrade.ui.theme.Typography

@Composable
fun BalanceSheet(
    cashAndShortTermInvestment: String,
    cashAndShortTermInvestmentYnYChange: String,
    totalAssets: String,
    totalAssetsYnYChange: String,
    totalLiabilities: String,
    totalLiabilitiesYnYChange: String,
    totalEquity: String,
    totalEquityYnYChange: String,
    sharesOutstanding: String,
    sharesOutstandingYnYChange: String,
    PreviousYear: String,
    isVisible: Boolean,
    onRowClick: () -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 13.dp, 0.dp, 13.dp)
                .clickable { onRowClick() },
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(
                text = "Balance Statement",
                style = MaterialTheme.typography.h2
            )
//            Image(
//                painter = painterResource(id = R.drawable.ic_expand),
//                contentDescription = "Expand Icon",
//                modifier = Modifier.size(24.dp)
//            )
        }

        if (isVisible) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {

                Text(
                    text = "(USD)",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = PreviousYear,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Y/Y Changes",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1.4f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Cash and short term investment
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Cash and short term investment",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = cashAndShortTermInvestment,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = cashAndShortTermInvestmentYnYChange,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1.4f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Total Assets
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Total Assets",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = totalAssets,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = totalAssetsYnYChange,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1.4f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Total Liabilities
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Total Liabilities",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = totalLiabilities,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = totalLiabilitiesYnYChange,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1.4f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Total Equity
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Total Equity",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = totalEquity,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = totalEquityYnYChange,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1.4f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Shares Outstanding
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Shares Outstanding",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = sharesOutstanding,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = sharesOutstandingYnYChange,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1.4f)

                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}