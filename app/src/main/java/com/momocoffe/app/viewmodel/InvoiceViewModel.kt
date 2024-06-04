package com.momocoffe.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.data.InvoiceProduct
import com.momocoffe.app.network.data.InvoiceState
import com.momocoffe.app.network.database.Invoice


import com.momocoffe.app.network.database.InvoiceDao
import kotlinx.coroutines.launch

class InvoiceViewModel(
    private val invoiceDao: InvoiceDao
) :  ViewModel()  {

    val loadingInvoiceState = mutableStateOf(false)
    var state by mutableStateOf(InvoiceState())
        private set
    fun createInvoice(invoice: InvoiceProduct) {
        loadingInvoiceState.value = true
        val invoice = Invoice(
            0,
            name = invoice.name,
            email =invoice.email,
            propina = invoice.propina,
            type_payment = invoice.type_payment,
            mount_discount = invoice.mount_discount,
            cupon = invoice.cupon,
            iva = invoice.iva,
            sub_total = invoice.sub_total,
            total = invoice.total
        )

        viewModelScope.launch {
            invoiceDao.insertInvoice(invoice)
            state = state.copy(invoice = state.invoice)
            loadingInvoiceState.value = false
        }
    }
}