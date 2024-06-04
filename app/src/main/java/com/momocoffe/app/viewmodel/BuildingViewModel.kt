package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.dto.BuildingRequest
import com.momocoffe.app.network.dto.ClientEmailInvoiceRequest
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.BuildingResponse
import com.momocoffe.app.network.response.ClientEmailSMSResponse
import kotlinx.coroutines.launch

class BuildingViewModel : ViewModel() {
    val loadingState = mutableStateOf(false)
    private val apiEmailSmsService: ApiService = RetrofitHelper.apiLambdaService()
    private val apiService: ApiService = RetrofitHelper.apiService()
    val buildingResultState = mutableStateOf<Result<BuildingResponse>?>(null)
    val emailResultState =  mutableStateOf<Result<ClientEmailSMSResponse>?>(null)
    fun payment(invoice: BuildingRequest) {
        loadingState.value = true
        viewModelScope.launch {
            try {
                val response = apiService.createBuilding(invoice);
                if (response.isSuccessful) {
                    val buildingResponse: BuildingResponse? = response.body()
                    if (buildingResponse != null) {
                        Log.w("Result.BuildingViewModel", buildingResponse.toString())
                        buildingResultState.value = Result.success(buildingResponse)
                    }else{
                        buildingResultState.value = Result.failure(Exception("Empty response body"))
                    }
                }
            } catch (e: Exception) {
                Log.e("Result.BuildingViewModel", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.CategoryViewModel", "Finally")
            }
        }
    }

    fun sendClientEmailInvoice(clientEmail: ClientEmailInvoiceRequest){
            viewModelScope.launch {
                try {
                    val response = apiEmailSmsService.sendEmailInvoice(clientEmail)
                    if (response.isSuccessful) {
                        val emailResponse: ClientEmailSMSResponse? = response.body()
                        if (emailResponse != null) {
                            emailResultState.value = Result.success(emailResponse)
                        }else{
                            emailResultState.value = Result.failure(Exception("Empty response body"))
                        }
                    } else {
                        emailResultState.value = Result.failure(Exception("Email send invoice failed"))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("Result.BuildingViewModel", e.message.toString())
                }
            }

    }
}