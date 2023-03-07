package com.example.currency_rate.presentation.util

import com.example.currency_rate.R

object CurrencyToIconConverter {

    fun convertCurrencyToIcon(currency: String): Int {
        return when (currency) {
            "AUDCAD" -> R.drawable.ic_audcad
            "EURUSD" -> R.drawable.ic_eurusd
            "GBPJPY" -> R.drawable.ic_gbpjpy
            "JPYAED" -> R.drawable.ic_jpyaed
            "JPYSEK" -> R.drawable.ic_jpysek
            "USDCAD" -> R.drawable.ic_usdcad
            else -> R.drawable.ic_usdgbp
        }
    }
}