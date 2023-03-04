package com.example.currency_rate.di

import com.example.currency_rate.data.source.remote.RatesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRatesApi(retrofit: Retrofit): RatesApi {
        return retrofit.create(RatesApi::class.java)
    }
}