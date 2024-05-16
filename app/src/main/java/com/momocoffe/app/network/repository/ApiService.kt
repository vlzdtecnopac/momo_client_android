package com.momocoffe.app.network.repository

import com.momocoffe.app.network.dto.LoginRequest
import com.momocoffe.app.network.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/users/employee/login")
    suspend fun getLogin(@Body loginDto: LoginRequest) : Response<LoginResponse>
}