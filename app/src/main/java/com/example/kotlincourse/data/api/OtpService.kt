package com.example.kotlincourse.data.api
import retrofit2.Response
import com.example.kotlincourse.data.model.OtpRequest
import com.example.kotlincourse.data.model.OtpResponse
import com.example.kotlincourse.data.model.OtpVerifyRequest
import com.example.kotlincourse.data.model.OtpVerifyResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OtpService {
    @POST("mitraLoginData")
    suspend fun sendOtp(@Body request: OtpRequest): Response<OtpResponse>

    @POST("validateOtp")
    suspend fun verifyOtp(@Body request: OtpVerifyRequest): Response<OtpVerifyResponse>
}


