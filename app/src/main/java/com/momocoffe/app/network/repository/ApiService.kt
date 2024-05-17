package com.momocoffe.app.network.repository

import com.momocoffe.app.network.dto.ClientRequest
import com.momocoffe.app.network.dto.LoginRequest
import com.momocoffe.app.network.dto.RefreshToken
import com.momocoffe.app.network.response.ClientResponse
import com.momocoffe.app.network.response.EmployeeResponse
import com.momocoffe.app.network.response.KioskoResponse
import com.momocoffe.app.network.response.LoginResponse
import com.momocoffe.app.network.response.RefreshTokenResponse
import com.momocoffe.app.network.response.ShoppingResponse
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

    @GET("/shopping/")
    suspend fun getShopping(@Query("shopping_id") shoppingID: String): Response<ShoppingResponse>

    @GET("/kioskos/activate/")
    suspend fun activateKiosko(@Query("shopping_id") shoppingID: String, @Query("state") state: Boolean): Response<KioskoResponse>

    @POST("/users/client/register")
    suspend fun getRegisterClient(@Body clientDto: ClientRequest) : Response<ClientResponse>

    @GET("/users/client")
    suspend fun getClient(@Query("email") email: String, @Query("phone") phone: String, @Query("client_id") clientID: String) : Response<ClientResponse>

}