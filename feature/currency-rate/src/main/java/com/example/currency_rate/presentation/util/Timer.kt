package com.example.currency_rate.presentation.util

import kotlinx.coroutines.flow.Flow

interface Timer {
    suspend fun createTimer(): Flow<Any?>
}