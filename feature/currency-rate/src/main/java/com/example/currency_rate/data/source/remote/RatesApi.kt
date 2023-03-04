package com.example.currency_rate.data.source.remote

import com.example.currency_rate.data.dto.RateDto
import retrofit2.http.GET

interface RatesApi {

    @GET("code-challenge/index.php")
    suspend fun getRates():List<RateDto>
}