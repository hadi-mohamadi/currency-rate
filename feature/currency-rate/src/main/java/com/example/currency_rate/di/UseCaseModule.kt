package com.example.currency_rate.di

import com.example.currency_rate.domain.RateRepository
import com.example.currency_rate.usecase.GetRatesUseCase
import com.example.currency_rate.usecase.GetRatesUseCaseImpl
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
    fun provideGetRates(rateRepository: RateRepository): GetRatesUseCase {
        return GetRatesUseCaseImpl(rateRepository)
    }
}