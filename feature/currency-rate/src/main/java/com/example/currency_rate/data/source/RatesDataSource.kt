package com.example.currency_rate.data.source

import com.example.currency_rate.data.dto.ResponseDto

interface RatesDataSource {
    suspend fun getRates(): ResponseDto
}