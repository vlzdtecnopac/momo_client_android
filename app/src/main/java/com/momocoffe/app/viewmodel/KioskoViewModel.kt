package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.dto.VerifyKioskoRequest
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.DataKiosko
import com.momocoffe.app.network.response.KioskoResponse
import kotlinx.coroutines.launch

class KioskoViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val loadingState = mutableStateOf(false)
    val kioskoResultState = mutableStateOf<Result<KioskoResponse>?>(null)
    val kioskoVefiryResultState = mutableStateOf<Result<DataKiosko>?>(null)


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
                    kioskoResultState.value = Result.failure(Exception("Active Kiosko failed"))
                }
            } catch (e: Exception) {
                Log.e("Result.KioskoModel", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.KioskoModel", "Finally")
            }
        }
    }

    fun verifyKiosko(kioskoId: String){
        loadingState.value = true
        viewModelScope.launch {
            try{
                val response = apiService.verifyKiosko(VerifyKioskoRequest(kioskoId))
                if (response.isSuccessful) {
                    val kioskoResponse: ArrayList<DataKiosko>? = response.body()
                    if (kioskoResponse != null) {
                        kioskoVefiryResultState.value = Result.success(kioskoResponse[0])
                    } else {
                        kioskoVefiryResultState.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    kioskoVefiryResultState.value = Result.failure(Exception("Active Kiosko failed"))
                }
            } catch (e: Exception) {
                Log.e("Result.KioskoModel", e.message.toString())
            } finally {
                loadingState.value = false
                Log.d("Result.KioskoModel", "Finally")
            }
        }
    }
}