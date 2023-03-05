package com.example.currency_rate.usecase

import com.example.currency_rate.domain.RateRepository
import com.example.currency_rate.domain.model.Rate

class GetRatesImpl(private val rateRepository: RateRepository):GetRates {
    override suspend fun invoke(): List<Rate> {
        return rateRepository.getRates()
    }
}