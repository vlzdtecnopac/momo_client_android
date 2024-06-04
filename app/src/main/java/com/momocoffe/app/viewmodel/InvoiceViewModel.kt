package com.momocoffe.app.viewmodel

import androidx.lifecycle.ViewModel
import com.momocoffe.app.network.database.CartDao
import com.momocoffe.app.network.database.InvoiceDao

class InvoiceViewModel(
    private val dao: InvoiceDao
) :  ViewModel()  {
}