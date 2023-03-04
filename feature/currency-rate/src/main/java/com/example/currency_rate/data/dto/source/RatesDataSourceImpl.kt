package com.example.currency_rate.data.dto.source

import com.example.currency_rate.data.dto.RateDto
import com.example.currency_rate.data.dto.source.remote.RatesApi
import javax.inject.Inject

class RatesDataSourceImpl @Inject constructor(private val getRatesApi: RatesApi):RatesDataSource {
    override suspend fun getRates(): List<RateDto> {
        return getRatesApi.getRates()
    }
}