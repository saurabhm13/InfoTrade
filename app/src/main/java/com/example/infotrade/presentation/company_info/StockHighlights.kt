package com.example.infotrade.presentation.company_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StockHighlights(
    previousClose: String,
    dayRange: String,
    marketCap: String,
    peRatio: String,
    dividendYield: String,
    primaryExchange: String
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
    ) {

        // Previous Close
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "PREVIOUS CLOSE",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
            Text(
                text = previousClose,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Day Range
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "DAY RANGE",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
            Text(
                text = dayRange,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Market Cap
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "MARKET CAP",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
            Text(
                text = marketCap,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // P/E Ratio
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "P/E RATIO",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
            Text(
                text = peRatio,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Dividend yield
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "DIVIDEND YIELD",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
            Text(
                text = dividendYield,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Primary Exchange
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "PRIMARY EXCHANGE",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
            Text(
                text = primaryExchange,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

    }



}