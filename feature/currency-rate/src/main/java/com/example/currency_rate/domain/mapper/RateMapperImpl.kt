package com.example.currency_rate.domain.mapper

import com.example.currency_rate.data.dto.RateDto
import com.example.currency_rate.domain.model.Rate

class RateMapperImpl : RateMapper {
    private fun mapToDomainModel(rateDto: RateDto): Rate {
        return Rate(symbol = rateDto.symbol, price = rateDto.price)
    }

    override fun mapToDomainModelList(list: List<RateDto>): List<Rate> {
        return arrayListOf<Rate>().apply {
            list.forEach {
                this.add(mapToDomainModel(it))
            }
        }
    }
}