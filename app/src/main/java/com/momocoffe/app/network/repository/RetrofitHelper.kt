package com.momocoffe.app.network.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(TokenInterceptor()).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://momocoffe-lb-1774679013.us-east-1.elb.amazonaws.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun apiService() : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = "tu_token_aqui"

        val modifiedRequest = originalRequest.newBuilder()
            .header("x-token", token)
            .build()

        return chain.proceed(modifiedRequest)
    }
}