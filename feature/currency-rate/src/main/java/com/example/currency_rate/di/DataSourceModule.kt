package com.example.currency_rate.di

import com.example.currency_rate.data.source.RatesDataSource
import com.example.currency_rate.data.source.RatesDataSourceImpl
import com.example.currency_rate.data.source.remote.RatesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRateDataSource(ratesApi: RatesApi): RatesDataSource {
        return RatesDataSourceImpl(ratesApi)
    }
}