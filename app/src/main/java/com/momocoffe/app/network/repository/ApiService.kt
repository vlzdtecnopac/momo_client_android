package com.momocoffe.app.network.repository

import com.momocoffe.app.network.dto.ClientReceptorEmailRequest
import com.momocoffe.app.network.dto.ClientReceptorSMSRequest
import com.momocoffe.app.network.dto.ClientRequest
import com.momocoffe.app.network.dto.ClientSessionEmailRequest
import com.momocoffe.app.network.dto.ClientSessionPhoneRequest
import com.momocoffe.app.network.dto.LoginRequest
import com.momocoffe.app.network.dto.RefreshToken
import com.momocoffe.app.network.dto.VerifyKioskoRequest
import com.momocoffe.app.network.response.CategoriesResponse
import com.momocoffe.app.network.response.ClientEmailSMSResponse
import com.momocoffe.app.network.response.ClientGeneralResponse
import com.momocoffe.app.network.response.ClientResponse
import com.momocoffe.app.network.response.ClientSessionResponse
import com.momocoffe.app.network.response.DataKiosko
import com.momocoffe.app.network.response.EmployeeResponse
import com.momocoffe.app.network.response.KioskoResponse
import com.momocoffe.app.network.response.LoginResponse
import com.momocoffe.app.network.response.ProductOptionsResponse
import com.momocoffe.app.network.response.ProductsResponse
import com.momocoffe.app.network.response.RefreshTokenResponse
import com.momocoffe.app.network.response.ShoppingResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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
    suspend fun getClient(@Query("email") email: String, @Query("phone") phone: String, @Query("client_id") clientID: String) : Response<ClientGeneralResponse>

    @POST("/kioskos/verify")
    suspend fun verifyKiosko(@Body requestBody: VerifyKioskoRequest): Response<ArrayList<DataKiosko>>

    @POST("/users/client/login")
    suspend fun getClientEmailSession(@Body requestBody: ClientSessionEmailRequest): Response<ArrayList<ClientSessionResponse>>

    @POST("/users/client/login")
    suspend fun getClientPhoneSession(@Body requestBody: ClientSessionPhoneRequest): Response<ArrayList<ClientSessionResponse>>

    @POST("/")
    suspend fun getClientEmailConfirm(@Body requestBody: ClientReceptorEmailRequest): Response<ClientEmailSMSResponse>

    @POST("/sms")
    suspend fun getClientSMSConfirm(@Body requestBody: ClientReceptorSMSRequest): Response<ClientEmailSMSResponse>

    @GET("/category/")
    suspend fun getCategories(): Response<ArrayList<CategoriesResponse>>

    @GET("/product/")
    suspend fun getProductsCategoryAndSubcategory( @Query("shopping_id") shoppingId: String,  @Query("categorys") category: String, @Query("subcategory") subcategory: String): Response<ProductsResponse>

    @GET("/product/")
    suspend fun getProductsCategory( @Query("shopping_id") shoppingId: String,  @Query("categorys") category: String): Response<ProductsResponse>

    @GET("/product/")
    suspend fun getProductByID( @Query("product_id") productId: String, @Query("shopping_id") shoppingId: String): Response<ProductsResponse>

    @GET("/product/options/{product_id}")
    suspend fun getProductsOptions( @Path("product_id") productId: String?): Response<ProductOptionsResponse>
}