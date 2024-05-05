package com.momocoffe.mx.network.repository

import com.momocoffe.mx.network.dto.LoginDto
import com.momocoffe.mx.network.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth")
    suspend fun getLogin(@Body loginDto: LoginDto) : Response<TokenDto>
}