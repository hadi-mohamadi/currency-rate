package com.example.currency_rate.di

import com.example.currency_rate.presentation.util.Time
import com.example.currency_rate.presentation.util.TimeImpl
import com.example.currency_rate.presentation.util.Timer
import com.example.currency_rate.presentation.util.TimerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UtilModule {

    @Provides
    @Singleton
    fun provideTimer(): Timer {
        return TimerImpl()
    }

    @Provides
    @Singleton
    fun provideTime(): Time {
        return TimeImpl()
    }
}