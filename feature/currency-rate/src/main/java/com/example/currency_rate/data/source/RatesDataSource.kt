package com.example.currency_rate.data.source

import com.example.currency_rate.data.dto.RateDto

interface RatesDataSource {
    suspend fun getRates():List<RateDto>
}