package com.example.kotlincourse.data.repository

import com.example.kotlincourse.data.api.ConsumerService
import com.example.kotlincourse.data.model.ConsumerRequest
import com.example.kotlincourse.data.model.ConsumerResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


// Repository Class
class ConsumerRepository {

    private val api: ConsumerService by lazy {
        Retrofit.Builder()
            .baseUrl("https://collectionapi.tpcentralodisha.com/") // ⛳ Replace with actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConsumerService::class.java)
    }

    suspend fun getConsumerData(request: ConsumerRequest): Response<ConsumerResponse> {
        return api.getConsumerData(request)
    }
}
