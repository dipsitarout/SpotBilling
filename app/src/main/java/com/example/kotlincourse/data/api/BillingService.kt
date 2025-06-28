package com.example.kotlincourse.data.api

import com.example.kotlincourse.data.model.SpotBillingRequest
import retrofit2.http.GET
import com.example.kotlincourse.data.model.SpotBillingResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface BillingService {
    @POST("sbm/download-spot-billing-data")
    suspend fun getUserData(
        @Header("Authorization") bearerToken: String,
        @Body requestData: SpotBillingRequest
    ): Response<SpotBillingResponse>
}