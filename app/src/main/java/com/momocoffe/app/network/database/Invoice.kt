package com.momocoffe.app.network.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoice")
data class Invoice(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "propina") val propina: String,
    @ColumnInfo(name = "type_payment") val type_payment: String,
    @ColumnInfo(name = "mount_discount") val mount_discount: String,
    @ColumnInfo(name = "cupon") val cupon: Int,
    @ColumnInfo(name = "iva") val iva: String,
    @ColumnInfo(name = "subtotal") val subTotal: String,
    @ColumnInfo(name = "total") val total: String,
    @ColumnInfo(name = "products") val products: String,
)