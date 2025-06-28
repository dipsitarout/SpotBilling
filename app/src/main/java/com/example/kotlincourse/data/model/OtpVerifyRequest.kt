package com.example.kotlincourse.data.model

data class OtpVerifyRequest(
    val mobileNo: String,
    val otp: String
)