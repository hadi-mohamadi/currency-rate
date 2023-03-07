package com.example.currency_rate.di

import com.example.currency_rate.data.source.RatesDataSource
import com.example.currency_rate.domain.RateRepository
import com.example.currency_rate.domain.RateRepositoryImpl
import com.example.currency_rate.domain.mapper.ExceptionMapper
import com.example.currency_rate.domain.mapper.RateMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRateRepository(
        rateMapper: RateMapper,
        exceptionMapper: ExceptionMapper,
        ratesDataSource: RatesDataSource
    ): RateRepository {
        return RateRepositoryImpl(rateMapper, exceptionMapper, ratesDataSource)
    }
}