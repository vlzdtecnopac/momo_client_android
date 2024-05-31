package com.momocoffe.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.data.CreatePedidoResponse
import com.momocoffe.app.network.dto.Extra
import com.momocoffe.app.network.dto.ExtraCoffee
import com.momocoffe.app.network.dto.Lid
import com.momocoffe.app.network.dto.Milk
import com.momocoffe.app.network.dto.PedidoRequest
import com.momocoffe.app.network.dto.Producto
import com.momocoffe.app.network.dto.Size
import com.momocoffe.app.network.dto.Sugar
import com.momocoffe.app.network.dto.Temperature
import com.momocoffe.app.network.dto.productosToString
import com.momocoffe.app.network.repository.ApiService
import com.momocoffe.app.network.repository.RetrofitHelper
import kotlinx.coroutines.launch

class PedidoViewModel: ViewModel(){
    private val apiService: ApiService = RetrofitHelper.apiService()
    val loadingState = mutableStateOf(false)
    val pedidoResultState = mutableStateOf<Result<CreatePedidoResponse>?>(null)

    fun create(pedidoData: PedidoRequest){
        loadingState.value = true
        viewModelScope.launch {
            try{
                Log.e("Result.PedidoViewModel", pedidoData.toString() )
                val response =  apiService.createPedido(pedidoData)
                Log.e("Result.PedidoViewModel", response.toString() )
                if(response.isSuccessful){
                    val pedidoResponse: CreatePedidoResponse? = response.body()
                    if(pedidoResponse != null){
                        pedidoResultState.value = Result.success(pedidoResponse)
                    }else{
                       pedidoResultState.value = Result.failure(Exception("Empty response body"))
                    }
                }else{
                    pedidoResultState.value = Result.failure(Exception("Pedido failed"))
                }
            }catch (e: Exception){
                pedidoResultState.value = Result.failure(e)
                Log.e("Result.PedidoViewModel",  e.message.toString())
            }finally {
                loadingState.value = false
                Log.d("Result.PedidoViewModel", "Finally")
            }
        }
    }
}