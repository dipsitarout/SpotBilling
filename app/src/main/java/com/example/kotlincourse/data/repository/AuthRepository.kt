package com.example.kotlincourse.data.repository

import com.example.kotlincourse.data.api.ApiService
import com.example.kotlincourse.data.model.LoginRequest
import com.example.kotlincourse.data.model.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepository {

    private val api: ApiService = Retrofit.Builder()
        .baseUrl("https://javaqas.tpcentralodisha.com/tatapower-ws-qas/users/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    suspend fun login(user_id: String, password: String, type: String, moduleId: String): Response<LoginResponse> {
        return api.login(LoginRequest(user_id, password, type, moduleId))
    }
}
