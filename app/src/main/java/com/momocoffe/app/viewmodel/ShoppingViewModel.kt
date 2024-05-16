package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.ShoppingResponse
import kotlinx.coroutines.launch

class ShoppingViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val loadingState = mutableStateOf(false)
    val shoppingResultState = mutableStateOf<Result<ShoppingResponse>?>(null)
    fun getShopping(shoppingID: String) {
        loadingState.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getShopping(shoppingID);
                if (response.isSuccessful) {
                    val shoppingResponse: ShoppingResponse? = response.body()
                    if (shoppingResponse != null) {
                        shoppingResultState.value = Result.success(shoppingResponse)
                    } else {
                        shoppingResultState.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    shoppingResultState.value = Result.failure(Exception("Shopping failed"))
                }
            } catch (e: Exception) {
                Log.e("Result.ShoppingModel", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.ShoppingModel", "Finally")
            }
        }
    }
}