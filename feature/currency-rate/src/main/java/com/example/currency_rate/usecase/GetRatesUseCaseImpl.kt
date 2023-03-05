package com.example.currency_rate.usecase

import com.example.currency_rate.domain.RateRepository
import com.example.currency_rate.domain.model.Rate

class GetRatesUseCaseImpl(private val rateRepository: RateRepository):GetRatesUseCase {
    override suspend fun invoke(): List<Rate> {
        return rateRepository.getRates()
    }
}