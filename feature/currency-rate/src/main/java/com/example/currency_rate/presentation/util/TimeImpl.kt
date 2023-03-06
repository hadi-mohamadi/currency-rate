package com.example.currency_rate.presentation.util

class TimeImpl:Time {
    override fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}