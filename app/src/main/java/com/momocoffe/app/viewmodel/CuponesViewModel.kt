package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.dto.ClientRequest
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.ClientResponse
import com.momocoffe.app.network.response.CuponesResponse
import kotlinx.coroutines.launch

class CuponesViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val cuponResultState = mutableStateOf<Result<CuponesResponse>?>(null)
    val loadingState = mutableStateOf(false)

    fun cuponValidate(cuponCode: String) {
        loadingState.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getCuponMomo(cuponCode)
                if (response.isSuccessful) {
                    val cuponResponse: CuponesResponse? = response.body()
                    if (cuponResponse != null) {
                        cuponResultState.value = Result.success(cuponResponse)
                    } else {
                        cuponResultState.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    cuponResultState.value = Result.failure(Exception("Client Session failed"))
                }

            } catch (e: Exception) {
                Log.e("Result.ClientViewModel", e.message.toString())
            }finally {
                loadingState.value = false
                Log.d("Result.ClientViewModel", "Finally")
            }
        }
    }

}