package com.example.currency_rate.di

import com.example.currency_rate.domain.RateRepository
import com.example.currency_rate.usecase.GetRates
import com.example.currency_rate.usecase.GetRatesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetRates(rateRepository: RateRepository): GetRates {
        return GetRatesImpl(rateRepository)
    }
}