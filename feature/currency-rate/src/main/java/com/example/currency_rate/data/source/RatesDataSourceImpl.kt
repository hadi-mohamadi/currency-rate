package com.example.currency_rate.data.source

import com.example.currency_rate.data.dto.RateDto
import com.example.currency_rate.data.source.remote.RatesApi

class RatesDataSourceImpl(private val getRatesApi: RatesApi) : RatesDataSource {
    override suspend fun getRates(): List<RateDto> {
        return getRatesApi.getRates()
    }
}