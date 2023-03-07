package com.example.currency_rate.presentation.ratelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currency_rate.R
import com.example.currency_rate.presentation.theme.*
import com.example.currency_rate.presentation.util.CurrencyToIconConverter

@Composable
fun RateListScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<RateListViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (uiState.value.isLoading) {
            LoadingContent()
        } else if (uiState.value.error != null) {
            ErrorContent(errorMessage = uiState.value.error!!)
        } else {
            LazyColumn(
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 0.dp,
                    end = 24.dp,
                    bottom = 0.dp
                ), contentPadding = PaddingValues(top = 76.dp, bottom = 44.dp)
            ) {
                item {
                    HeaderContent()
                    Spacer(modifier.size(24.dp))
                }
                items(items = uiState.value.rateList!!) { item ->
                    RateItem(rate = item)
                    Spacer(modifier = Modifier.size(16.dp))
                }
                item {
                    Spacer(modifier = Modifier.size(69.dp))
                    UpdateContent(lastUpdatedTime = uiState.value.lastUpdateTime!!)
                }
            }
        }
    }

}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingContentPreview() {
    LoadingContent()
}

@Composable
private fun ErrorContent(modifier: Modifier = Modifier, errorMessage: String) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            fontFamily = satoshiBold,
            color = Red200,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            text = errorMessage
        )
    }
}

@Preview
@Composable
private fun ErrorContentPreview() {
    ErrorContent(errorMessage = "no-connectivity")
}

@Preview
@Composable
private fun RateListScreenPreview() {
    RateListScreen()
}

@Composable
private fun HeaderContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            fontFamily = satoshiBold,
            color = Color.Black,
            fontSize = 48.sp,
            text = RatesListTitle
        )
    }
}

@Preview
@Composable
private fun HeaderContentPreview() {
    HeaderContent()
}

@Composable
private fun RateItem(modifier: Modifier = Modifier, rate: FormattedRateModel) {
    Row(
        modifier = modifier.background(
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            ), color = Gray200
        ), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp, top = 13.dp, end = 8.dp, bottom = 13.dp)
                .size(44.dp),
            painter = painterResource(
                id = CurrencyToIconConverter.convertCurrencyToIcon(
                    rate.symbol.replace(
                        "/",
                        ""
                    )
                )
            ),
            contentDescription = null
        )
        Text(fontFamily = satoshiBold, color = Color.Black, fontSize = 18.sp, text = rate.symbol)
        Spacer(Modifier.weight(1F))
        Text(
            fontFamily = satoshiMedium,
            color = Green200,
            fontSize = 20.sp,
            text = rate.price.toString()
        )
        if (rate.changeState != 0) {
            Spacer(modifier = Modifier.size(5.dp))
            Image(
                modifier = Modifier
                    .size(44.dp),
                painter = painterResource(
                    if (rate.changeState > 0) {
                        R.drawable.ic_up_arrow
                    } else {
                        R.drawable.ic_down_arrow
                    }
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(27.dp))
        } else {
            Spacer(Modifier.size(76.dp))
        }

    }
}

@Preview
@Composable
private fun RateItemPreview() {
    RateItem(rate = FormattedRateModel(symbol = "EUR/USD", price = 0.1632, changeState = 1))
}

@Composable
private fun UpdateContent(modifier: Modifier = Modifier, lastUpdatedTime: String) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            fontFamily = satoshiRegular,
            color = Gray100,
            fontSize = 12.sp,
            text = UpdateTimePrefix + lastUpdatedTime
        )
    }
}

@Preview
@Composable
private fun UpdateContentPreview() {
    UpdateContent(lastUpdatedTime = "06/03/2023 - 02:14")
}