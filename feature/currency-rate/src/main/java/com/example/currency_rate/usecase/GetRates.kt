package com.example.currency_rate.usecase

import com.example.currency_rate.domain.model.Rate

interface GetRates {
    suspend operator fun invoke():List<Rate>
}