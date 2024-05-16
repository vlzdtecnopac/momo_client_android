package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.dto.LoginRequest
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import com.momocoffe.app.network.response.EmployeeResponse
import com.momocoffe.app.network.response.LoginResponse
import kotlinx.coroutines.launch

class WelcomeViewModel(): ViewModel()  {
    private val apiService: ApiService = RetrofitHelper.apiService()
    val loadingState = mutableStateOf(false)
    val employeeResultState = mutableStateOf<Result<EmployeeResponse>?>(null)

    fun getEmployee(employeeID: String){
        loadingState.value = true
        viewModelScope.launch{
            try{
                val response = apiService.getEmployee(employeeID);
                if(response.isSuccessful){
                    val employeeResponse: EmployeeResponse? = response.body()
                    if(employeeResponse != null){
                       employeeResultState.value = Result.success(employeeResponse)
                    } else {
                        employeeResultState.value = Result.failure(Exception("Empty response body"))
                    }
                }else{
                    employeeResultState.value = Result.failure(Exception("Employee failed"))
                }
            }catch (e: Exception){
                Log.e("Result.WelcomeModel", e.message.toString())
            }finally {
                loadingState.value = false
                Log.d("Result.WelcomeModel", "Finally")
            }
        }
    }
}