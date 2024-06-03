package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.dto.BuildingRequest
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.BuildingResponse
import com.momocoffe.app.network.response.CategoriesResponse
import kotlinx.coroutines.launch

class BuildingViewModel : ViewModel() {
    val loadingState = mutableStateOf(false)
    private val apiService: ApiService = RetrofitHelper.apiService()
    val buildingResultState = mutableStateOf<Result<BuildingResponse>?>(null)
    fun payment(invoice: BuildingRequest) {
        loadingState.value = true
        viewModelScope.launch {
            try {
                val response = apiService.createBuilding(invoice);
                if (response.isSuccessful) {
                    val buildingResponse: BuildingResponse? = response.body()
                    if (buildingResponse != null) {
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
}