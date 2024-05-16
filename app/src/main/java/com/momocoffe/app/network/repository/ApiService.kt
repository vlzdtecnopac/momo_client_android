package com.momocoffe.app.network.repository

import com.momocoffe.app.network.dto.LoginRequest
import com.momocoffe.app.network.response.EmployeeResponse
import com.momocoffe.app.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/users/employee/login")
    suspend fun getLogin(@Body loginDto: LoginRequest) : Response<LoginResponse>

    @GET("/users/employee/")
    suspend fun getEmployee(@Query("employee_id") employeeID: String): Response<EmployeeResponse>
}