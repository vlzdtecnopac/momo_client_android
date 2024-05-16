package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.KioskoResponse
import com.momocoffe.app.network.response.ShoppingResponse
import kotlinx.coroutines.launch

class KioskoModel : ViewModel() {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val loadingState = mutableStateOf(false)
    val kioskoResultState = mutableStateOf<Result<KioskoResponse>?>(null)

    fun activeKiosko(shoppingID: String) {
        loadingState.value = true
        viewModelScope.launch {
            try{
                val response = apiService.activateKiosko(shoppingID, state = false)
                if (response.isSuccessful) {
                    val kioskoResponse: KioskoResponse? = response.body()
                    if (kioskoResponse != null) {
                        kioskoResultState.value = Result.success(kioskoResponse)
                    } else {
                        kioskoResultState.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    kioskoResultState.value = Result.failure(Exception("Shopping failed"))
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