package com.example.kotlincourse.data.model


data class OtpRequest(
    val CA: String = "80000427247",
    val EMAIL: String,
    val MOB_NUM: String,
    val REF_CODE: String = ""
)
