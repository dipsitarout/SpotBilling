package com.example.kotlincourse.data.repository

import com.example.kotlincourse.data.api.BillingService
import com.example.kotlincourse.data.model.SpotBillingRequest
import com.example.kotlincourse.data.model.SpotBillingResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST





class SpotBillingRepository {

    private val apiService: BillingService by lazy {
        Retrofit.Builder()
            .baseUrl("https://javaqas.tpcentralodisha.com/tatapower-ws-qas/") // Only base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BillingService::class.java)
    }

    suspend fun fetchUserData(token: String, userId: String): Response<SpotBillingResponse> {
        val bearer = "Bearer $token"
        val requestData = SpotBillingRequest(
            user_id = userId,
            type = "b",
            consumer_type = "S",
            search_flag = "S",
            version = "1.0.0"
        )
        return apiService.getUserData(bearer, requestData)
    }
}
