package com.momocoffe.app.network.repository

import com.momocoffe.app.network.dto.LoginRequest
import com.momocoffe.app.network.dto.RefreshToken
import com.momocoffe.app.network.response.EmployeeResponse
import com.momocoffe.app.network.response.LoginResponse
import com.momocoffe.app.network.response.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/users/update_token")
    suspend fun getRefreshToken(@Body refreshDto: RefreshToken) : Response<RefreshTokenResponse>

    @POST("/users/employee/login")
    suspend fun getLogin(@Body loginDto: LoginRequest) : Response<LoginResponse>

    @GET("/users/employee/")
    suspend fun getEmployee(@Query("employee_id") employeeID: String): Response<EmployeeResponse>
}