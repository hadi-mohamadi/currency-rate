package com.example.currency_rate.domain

import com.example.currency_rate.data.source.RatesDataSource
import com.example.currency_rate.domain.mapper.ExceptionMapper
import com.example.currency_rate.domain.mapper.RateMapper
import com.example.currency_rate.domain.model.Rate

class RateRepositoryImpl(
    private val rateMapper: RateMapper,
    private val exceptionMapper: ExceptionMapper,
    private val ratesDataSource: RatesDataSource
) : RateRepository {
    override suspend fun getRates(): List<Rate> {
        try {
            return rateMapper.mapToDomainModelList(ratesDataSource.getRates().rates)
        } catch (e: Exception) {
            throw exceptionMapper.convertException(e)
        }
    }
}