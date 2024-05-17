package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.dto.ClientRequest
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.ClientGeneralResponse
import com.momocoffe.app.network.response.ClientResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class ClientViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val loadingState = mutableStateOf(false)
    val clientResultState = mutableStateOf<Result<ClientResponse>?>(null)
    val clientResultCheckEmailState = mutableStateOf<Result<ClientGeneralResponse>?>(null)
    fun register(clientDto: ClientRequest) {
        loadingState.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getRegisterClient(clientDto);
                Log.e("Result.ClientViewModel", response.toString())
                if (response.isSuccessful) {
                    val clientResponse: ClientResponse? = response.body()
                    if (clientResponse != null) {
                        clientResultState.value = Result.success(clientResponse)
                    } else {
                        clientResultState.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    clientResultState.value = Result.failure(Exception("Shopping failed"))
                }

            } catch (e: Exception) {
                Log.e("Result.ClientViewModel", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.ClientViewModel", "Finally")
            }
        }
    }

    fun getClient(email: String = "", phone: String = "",  clientID: String = ""){
        viewModelScope.launch {
            try {
                val response: Response<ClientGeneralResponse> = apiService.getClient(email, phone, clientID)
                if (response.isSuccessful) {
                    val clientResponse: ClientGeneralResponse? = response.body()
                    if (clientResponse != null) {
                        clientResultCheckEmailState.value = Result.success(clientResponse)
                    } else {
                        clientResultCheckEmailState.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    clientResultCheckEmailState.value = Result.failure(Exception("Shopping failed"))
                }
            } catch (e: Exception) {
                Log.e("Result.ClientViewModel", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.ClientViewModel", "Finally")
            }
        }
    }


}