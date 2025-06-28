package com.example.kotlincourse.data.api

import com.example.kotlincourse.data.model.LoginRequest
import com.example.kotlincourse.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
