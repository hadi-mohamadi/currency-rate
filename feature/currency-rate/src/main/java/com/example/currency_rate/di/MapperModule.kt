package com.example.currency_rate.di

import com.example.currency_rate.domain.mapper.ExceptionMapper
import com.example.currency_rate.domain.mapper.ExceptionMapperImpl
import com.example.currency_rate.domain.mapper.RateMapper
import com.example.currency_rate.domain.mapper.RateMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {

    @Provides
    @Singleton
    fun provideRateMapper(): RateMapper {
        return RateMapperImpl()
    }

    @Provides
    @Singleton
    fun provideExceptionMapper(): ExceptionMapper {
        return ExceptionMapperImpl()
    }
}