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

    fun create(){
        loadingState.value = true
        viewModelScope.launch {
            val pedidoData = PedidoRequest(
                name_client = "Andres Gonzales",
                shopping_id = "de86589e-255f-4987-8f6b-53845acfd154",
                kiosko_id = "eae76186-6702-4216-8225-cee64e380f38",
                columns_pending = 4,
                product = productosToString(listOf(
                    Producto(
                        id = "3caccd2e-9842-4239-aae2-a2a3d3cced5e",
                        name_product = "Flat white",
                        price = 40,
                        image = "https://lh3.googleusercontent.com/q7eTMbvJ6FDmdCr24yHaA2Vgr4A62HTSUMPQ2A5hL3U4_oHM2KZyQ7OyhYz7cMm16kuIHBc3afRVw-lRRj01kjUD9G3Sh_TcKQ",
                        extra = Extra(
                            size = Size("Chico", 0),
                            milk = Milk("Deslactosada", 5),
                            sugar = Sugar("Blanca", 0),
                            extra_coffee = listOf(ExtraCoffee("Extra shot No", "0")),
                            lid = listOf(Lid("Sin Tapa", 0)),
                            sauce = listOf(),
                            temperature = Temperature("", 0),
                            color = "",
                            coffee_type = Any()
                        ),
                        quanty = 1,
                        subtotal = 40
                    ),
                ))
            )
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