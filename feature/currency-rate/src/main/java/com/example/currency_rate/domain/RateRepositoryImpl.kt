package com.example.currency_rate.domain

import com.example.currency_rate.data.source.RatesDataSource
import com.example.currency_rate.domain.mapper.RateMapper
import com.example.currency_rate.domain.model.Rate

class RateRepositoryImpl(
    private val rateMapper: RateMapper,
    private val ratesDataSource: RatesDataSource
) : RateRepository {
    override suspend fun getRates(): List<Rate> {
        return rateMapper.mapToDomainModelList(ratesDataSource.getRates().rates)
    }
}