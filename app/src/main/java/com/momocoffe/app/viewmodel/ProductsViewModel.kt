package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.ProductsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductsViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val productsResultState = mutableStateOf<Result<ProductsResponse>?>(null)
    val loadingState = mutableStateOf(false)

    fun products(shopping_id: String = "", categorys: String = "", subcategory: String = "") {
        loadingState.value = true
        viewModelScope.launch{

            try{
                var response: Response<ProductsResponse>

                if(subcategory.isNotEmpty()){
                     response = apiService.getProductsCategoryAndSubcategory(shopping_id, category = categorys, subcategory)
                }else{
                     response = apiService.getProductsCategory(shopping_id, category = categorys)
                }

                if(response.isSuccessful){
                    val loginResponse: ProductsResponse? = response.body()
                    if(loginResponse != null){
                        productsResultState.value = Result.success(loginResponse)
                    } else {
                        productsResultState.value = Result.failure(Exception("Empty response body"))
                    }
                }else{
                    productsResultState.value = Result.failure(Exception("Products failed"))
                }
            } catch (e: Exception){
                productsResultState.value = Result.failure(e)
                Log.e("Result.ProductsModelView", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.ProductsModelView", "Finally")
            }
        }

    }
}