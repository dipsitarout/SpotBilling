    package com.example.kotlincourse.data.model


    data class OtpVerifyResponse(
        val response: OtpRes
    )
    data class OtpRes(
        val status: Boolean,
        val message: String,
        val responseCode: String
    )
