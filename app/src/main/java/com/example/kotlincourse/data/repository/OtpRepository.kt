package com.example.kotlincourse.data.repository
import com.example.kotlincourse.data.api.OtpService
import com.example.kotlincourse.data.model.OtpRequest
import com.example.kotlincourse.data.model.OtpVerifyRequest
import com.example.kotlincourse.data.model.OtpResp
import com.example.kotlincourse.data.model.OtpResponse
import com.example.kotlincourse.data.model.OtpVerifyResponse
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Response
import okhttp3.logging.HttpLoggingInterceptor



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OtpRepository {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val api: OtpService = Retrofit.Builder()
        .baseUrl("https://javaqas.tpcentralodisha.com/generate_ref_code/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", Credentials.basic("refCode_user", "referralpassword@123"))
                        .build()
                    chain.proceed(request)
                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OtpService::class.java)

    suspend fun sendOtp(request: OtpRequest): Response<OtpResponse> {
        return api.sendOtp(request)
    }

    suspend fun verifyOtp(request: OtpVerifyRequest): Response<OtpVerifyResponse> {
        return api.verifyOtp(request)
    }


}
