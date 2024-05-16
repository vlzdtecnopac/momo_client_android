package com.momocoffe.app.network.repository

import android.content.Context
import com.momocoffe.app.App
import com.momocoffe.app.BuildConfig
import com.momocoffe.app.ui.theme.MomoCoffeClientTheme
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(TokenInterceptor()).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun apiService() : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = App.instance.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null) ?: ""
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("x-token", token)
            .build()

        return chain.proceed(modifiedRequest)
    }
}