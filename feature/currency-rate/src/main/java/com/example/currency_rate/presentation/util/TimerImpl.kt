package com.example.currency_rate.presentation.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimerImpl:Timer {
    override suspend fun createTimer(): Flow<Any?> {
        return flow {
            while (true) {
                emit(null)
                delay(120000)
            }
        }
    }
}