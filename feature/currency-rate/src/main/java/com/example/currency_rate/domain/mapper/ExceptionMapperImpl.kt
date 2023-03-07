package com.example.currency_rate.domain.mapper

import java.net.UnknownHostException

class ExceptionMapperImpl : ExceptionMapper {
    override fun convertException(exception: Exception): Exception {
        if (exception is UnknownHostException) {
            return Exception("no-connectivity")
        }
        if (exception is IllegalStateException) {
            return Exception("invalid-data")
        }
        return exception
    }
}