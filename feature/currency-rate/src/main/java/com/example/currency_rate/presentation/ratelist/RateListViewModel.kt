package com.example.currency_rate.presentation.ratelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currency_rate.domain.model.Rate
import com.example.currency_rate.presentation.util.Time
import com.example.currency_rate.presentation.util.Timer
import com.example.currency_rate.usecase.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

data class FormattedRateModel(val symbol: String, val price: Double, val changeState: Int)

data class UiState(
    val rateList: List<FormattedRateModel>?,
    val lastUpdateTime: String?,
    val isLoading: Boolean,
    val error: String?
)

@HiltViewModel
class RateListViewModel @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase,
    private val timer: Timer,
    private val time: Time
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        UiState(
            rateList = null,
            lastUpdateTime = null,
            isLoading = true,
            error = null
        )
    )

    val uiState = _uiState

    init {
        startGettingRateList()
    }

    private suspend fun loadRateList() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        runCatching {
            getRatesUseCase()
        }.onSuccess {
            val rateList = convertRowRateListToFormattedRateModelList(it)
            val lastUpdateTime = convertMillisToDateTime(time.getCurrentTimeMillis())
            _uiState.value = _uiState.value.copy(
                rateList = rateList,
                lastUpdateTime = lastUpdateTime,
                isLoading = false,
                error = null
            )
        }.onFailure {
            _uiState.value = _uiState.value.copy(
                rateList = null,
                lastUpdateTime = null,
                isLoading = false,
                error = it.message
            )
        }
    }

    private fun convertRowRateListToFormattedRateModelList(rateList: List<Rate>): List<FormattedRateModel> {
        return arrayListOf<FormattedRateModel>().apply {
            rateList.forEach {
                val resultSymbol = getFormattedSymbol(it.symbol)
                val resultPrice = roundRatePrice(it.price)
                var changeState = 0
                val previousPrice = getCorrespondingRateUiModel(it.symbol)?.price
                if (previousPrice != null) {
                    changeState = if (it.price > previousPrice) {
                        1
                    } else if (it.price < previousPrice) {
                        -1
                    } else {
                        0
                    }
                }
                this.add(
                    FormattedRateModel(
                        symbol = resultSymbol,
                        price = resultPrice,
                        changeState = changeState
                    )
                )
            }
        }
    }

    private fun convertMillisToDateTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.US)
        return format.format(date)
    }

    private fun roundRatePrice(input: Double): Double {
        return (input * 10000.0).toInt() / 10000.0
    }

    private fun getFormattedSymbol(symbol: String): String {
        return symbol.substring(0, 3) + "/" + symbol.substring(3, 6)
    }

    private fun getCorrespondingRateUiModel(symbol: String): FormattedRateModel? {
        return _uiState.value.rateList?.find { it.symbol.replace("/", "") == symbol }
    }

    private fun startGettingRateList() {
        viewModelScope.launch {
            timer.createTimer().collect {
                loadRateList()
            }
        }
    }
}