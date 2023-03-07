package com.example.currency_rate.data.source

import com.example.currency_rate.data.dto.ResponseDto
import com.example.currency_rate.data.source.remote.RatesApi

class RatesDataSourceImpl(private val getRatesApi: RatesApi) : RatesDataSource {
    override suspend fun getRates(): ResponseDto {
        return getRatesApi.getRates()
    }
}