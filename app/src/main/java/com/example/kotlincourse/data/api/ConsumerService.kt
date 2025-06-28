package com.example.kotlincourse.data.api

import com.example.kotlincourse.data.model.ConsumerRequest
import com.example.kotlincourse.data.model.ConsumerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConsumerService {

    @POST("api/Info/getconsumerdetails") // 🔁 Replace with actual API endpoint
    suspend fun getConsumerData(
        @Body requestData: ConsumerRequest
    ): Response<ConsumerResponse>
}

