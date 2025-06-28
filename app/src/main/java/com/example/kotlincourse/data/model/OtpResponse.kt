package com.example.kotlincourse.data.model

data class OtpResponse(
    val response: OtpResp
)
data class OtpResp(
    val status: Boolean,
    val message: String,
    val responseCode: String
)
