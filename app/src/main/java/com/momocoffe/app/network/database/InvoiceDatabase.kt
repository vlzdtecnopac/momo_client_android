package com.momocoffe.app.network.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Invoice::class], version = 1)
abstract class InvoiceDatabase: RoomDatabase() {
    abstract val dao: InvoiceDao
}
