package com.example.currency_rate

import com.example.currency_rate.data.dto.RateDto
import com.example.currency_rate.domain.mapper.RateMapperImpl
import com.example.currency_rate.domain.model.Rate
import org.junit.Test

class RateMapperTest {
    @Test
    fun when_mapToDomainModelList_is_called_with_a_list_of_rateDto_its_return_type_content_must_be_as_the_same_of_input_list_in_rate_list() {
        val rateMapperImpl = RateMapperImpl()
        val inputList = listOf(
            RateDto(symbol = "EURUSD", price = 0.16337620324),
            RateDto(symbol = "GBPJPY", price = 0.14116733489)
        )
        val expectedList = listOf(
            Rate(symbol = "EURUSD", price = 0.16337620324),
            Rate(symbol = "GBPJPY", price = 0.14116733489)
        )
        val result=rateMapperImpl.mapToDomainModelList(inputList)
        assert(result == expectedList)
    }
}