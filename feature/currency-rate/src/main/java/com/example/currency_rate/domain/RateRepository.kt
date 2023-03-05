package com.example.currency_rate.domain

import com.example.currency_rate.domain.model.Rate

interface RateRepository {
    suspend fun getRates():List<Rate>
}