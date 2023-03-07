package com.example.currency_rate.domain.mapper

interface ExceptionMapper {
    fun convertException(exception: Exception): Exception
}