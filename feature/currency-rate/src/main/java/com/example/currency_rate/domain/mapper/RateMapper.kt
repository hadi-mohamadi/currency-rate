package com.example.currency_rate.domain.mapper

import com.example.currency_rate.data.dto.RateDto
import com.example.currency_rate.domain.model.Rate

interface RateMapper {
    fun mapToDomainModelList(list: List<RateDto>):List<Rate>
}