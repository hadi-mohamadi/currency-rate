package com.example.currency_rate

import com.example.currency_rate.domain.model.Rate
import com.example.currency_rate.presentation.ratelist.FormattedRateModel
import com.example.currency_rate.presentation.ratelist.RateListViewModel
import com.example.currency_rate.presentation.ratelist.UiState
import com.example.currency_rate.presentation.util.Time
import com.example.currency_rate.presentation.util.Timer
import com.example.currency_rate.usecase.GetRatesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class RateListViewModelTest {

    @RelaxedMockK
    lateinit var getRatesUseCase: GetRatesUseCase

    @RelaxedMockK
    lateinit var time: Time

    @RelaxedMockK
    lateinit var timer: Timer

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun createTimer(): Flow<Any?> {
        return flow {
            while (true) {
                emit(null)
                delay(2000)
            }
        }
    }

    @Test
    fun when_viewModel_init_uiState_must_contain_isLoading_equal_true_with_no_error_with_no_data() {
        runBlocking {
            coEvery { getRatesUseCase() } coAnswers {
                delay(2000)
                emptyList()
            }
            coEvery { timer.createTimer() } coAnswers { createTimer() }
            val viewModel = RateListViewModel(getRatesUseCase, timer, time)
            delay(1000)
            assert(
                viewModel.uiState.value == UiState(
                    rateList = null,
                    lastUpdateTime = null,
                    isLoading = true,
                    error = null
                )
            )
        }
    }

    @Test
    fun when_viewModel_init_and_getRatesUseCase_returns_error_uiState_must_contain_that_error_with_no_data_with_isLading_false() {
        runBlocking {
            coEvery { getRatesUseCase() } coAnswers {
                throw Exception("no-connectivity")
            }
            coEvery { timer.createTimer() } coAnswers { createTimer() }
            var viewModel = RateListViewModel(getRatesUseCase, timer, time)
            delay(1000)
            assert(
                viewModel.uiState.value == UiState(
                    rateList = null,
                    lastUpdateTime = null,
                    isLoading = false,
                    error = "no-connectivity"
                )
            )
            coEvery { getRatesUseCase() } coAnswers {
                throw Exception("invalid-data")
            }
            viewModel = RateListViewModel(getRatesUseCase, timer, time)
            delay(1000)
            assert(
                viewModel.uiState.value == UiState(
                    rateList = null,
                    lastUpdateTime = null,
                    isLoading = false,
                    error = "invalid-data"
                )
            )
        }
    }

    @Test
    fun when_viewModel_init_and_getRatesUseCase_returns_list_of_rates_uiState_must_contain_corresponding_data_with_no_error_with_isLading_false() {
        runBlocking {
            coEvery { time.getCurrentTimeMillis() } coAnswers { 1678056284110 }
            val useCaseResult = listOf(
                Rate(symbol = "EURUSD", price = 0.16337620324),
                Rate(symbol = "GBPJPY", price = 0.14116733489)
            )
            coEvery { getRatesUseCase() } coAnswers {
                useCaseResult
            }
            coEvery { timer.createTimer() } coAnswers { createTimer() }
            val expectedList = listOf(
                FormattedRateModel(symbol = "EUR/USD", price = 0.1633, 0),
                FormattedRateModel(symbol = "GBP/JPY", price = 0.1411, 0)
            )
            val viewModel = RateListViewModel(getRatesUseCase, timer, time)
            delay(1000)
            assert(
                viewModel.uiState.value == UiState(
                    rateList = expectedList,
                    lastUpdateTime = "06/03/2023 - 02:14",
                    isLoading = false,
                    error = null
                )
            )

        }
    }

    @Test
    fun when_viewModel_init_and_getRatesUseCase_returns_list_of_rates_bigger_than_previous_uiState_must_contain_corresponding_data_with_positive_changeState_with_no_error_with_isLading_false() {
        runBlocking {
            coEvery { time.getCurrentTimeMillis() } coAnswers { 1678056284110 }
            val useCaseFirstResult = listOf(
                Rate(symbol = "EURUSD", price = 0.16337620324),
                Rate(symbol = "GBPJPY", price = 0.14116733489)
            )

            coEvery { getRatesUseCase() } coAnswers {
                useCaseFirstResult
            }
            coEvery { timer.createTimer() } coAnswers { createTimer() }
            val viewModel = RateListViewModel(getRatesUseCase, timer, time)
            delay(1000)
            val useCaseSecondResult = listOf(
                Rate(symbol = "EURUSD", price = 0.17337620324),
                Rate(symbol = "GBPJPY", price = 0.16116733489)
            )
            coEvery { getRatesUseCase() } coAnswers {
                useCaseSecondResult
            }
            delay(2000)
            val expectedList = listOf(
                FormattedRateModel(symbol = "EUR/USD", price = 0.1733, 1),
                FormattedRateModel(symbol = "GBP/JPY", price = 0.1611, 1)
            )
            assert(
                viewModel.uiState.value == UiState(
                    rateList = expectedList,
                    lastUpdateTime = "06/03/2023 - 02:14",
                    isLoading = false,
                    error = null
                )
            )
        }
    }

    @Test
    fun when_viewModel_init_and_getRatesUseCase_returns_list_of_rates_less_than_previous_uiState_must_contain_corresponding_data_with_negative_changeState_with_no_error_isLading_false() {
        runBlocking {
            coEvery { time.getCurrentTimeMillis() } coAnswers { 1678056284110 }
            val useCaseFirstResult = listOf(
                Rate(symbol = "EURUSD", price = 0.16337620324),
                Rate(symbol = "GBPJPY", price = 0.14116733489)
            )

            coEvery { getRatesUseCase() } coAnswers {
                useCaseFirstResult
            }
            coEvery { timer.createTimer() } coAnswers { createTimer() }
            val viewModel = RateListViewModel(getRatesUseCase, timer, time)
            delay(1000)
            val useCaseSecondResult = listOf(
                Rate(symbol = "EURUSD", price = 0.15337620324),
                Rate(symbol = "GBPJPY", price = 0.13116733489)
            )
            coEvery { getRatesUseCase() } coAnswers {
                useCaseSecondResult
            }
            delay(2000)
            val expectedList = listOf(
                FormattedRateModel(symbol = "EUR/USD", price = 0.1533, -1),
                FormattedRateModel(symbol = "GBP/JPY", price = 0.1311, -1)
            )
            assert(
                viewModel.uiState.value == UiState(
                    rateList = expectedList,
                    lastUpdateTime = "06/03/2023 - 02:14",
                    isLoading = false,
                    error = null
                )
            )
        }
    }


}