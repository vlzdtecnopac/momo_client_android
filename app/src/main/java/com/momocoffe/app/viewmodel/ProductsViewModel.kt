package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.ProductOptionsResponse
import com.momocoffe.app.network.response.ProductsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductsViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val productsResultState = mutableStateOf<Result<ProductsResponse>?>(null)
    val productsOptionsResultState = mutableStateOf<Result<ProductOptionsResponse>?>(null)
    val calculatePriceResult = mutableStateOf(0)
    val loadingState = mutableStateOf(false)

    fun product(shopping_id: String = "", product_id: String? = "") {
        loadingState.value = true
        viewModelScope.launch {
            try {
                val response = product_id?.let { apiService.getProductByID(it, shopping_id) }
                if (response != null) {
                    if (response.isSuccessful) {
                        val productResponse: ProductsResponse? = response.body()
                        if (productResponse != null) {
                            productsResultState.value = Result.success(productResponse)
                        } else {
                            productsResultState.value =
                                Result.failure(Exception("Empty response body"))
                        }

                    } else {
                        productsResultState.value = Result.failure(Exception("Product failed"))
                    }
                }

            } catch (e: Exception) {
                productsResultState.value = Result.failure(e)
                Log.e("Result.ProductsModelView", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.ProductsModelView", "Finally")
            }

        }
    }

    fun products(shopping_id: String = "", categorys: String = "", subcategory: String = "") {
        loadingState.value = true
        viewModelScope.launch {
            try {
                var response: Response<ProductsResponse>

                if (subcategory.isNotEmpty()) {
                    response = apiService.getProductsCategoryAndSubcategory(
                        shopping_id,
                        category = categorys,
                        subcategory
                    )
                } else {
                    response = apiService.getProductsCategory(shopping_id, category = categorys)
                }

                if (response.isSuccessful) {
                    val productsResponse: ProductsResponse? = response.body()
                    if (productsResponse != null) {
                        productsResultState.value = Result.success(productsResponse)
                    } else {
                        productsResultState.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    productsResultState.value = Result.failure(Exception("Products failed"))
                }
            } catch (e: Exception) {
                productsResultState.value = Result.failure(e)
                Log.e("Result.ProductsModelView", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.ProductsModelView", "Finally")
            }
        }
    }

    fun productOptions(product_id: String?) {
        loadingState.value = true
        viewModelScope.launch {
            try {
                var response = apiService.getProductsOptions(productId = product_id)
                if(response.isSuccessful){
                    val productsResponse: ProductOptionsResponse? = response.body()
                    if (productsResponse != null) {
                        productsOptionsResultState.value = Result.success(productsResponse)
                    } else {
                        productsOptionsResultState.value = Result.failure(Exception("Empty response body"))
                    }
                }else {
                    productsOptionsResultState.value = Result.failure(Exception("Products failed"))
                }

            }catch (e: Exception){
                productsOptionsResultState.value = Result.failure(e)
                Log.e("Result.ProductsModelView", e.message.toString())
            }finally {
                loadingState.value = false
                Log.d("Result.ProductsModelView", "Finally")
            }
        }
    }
}