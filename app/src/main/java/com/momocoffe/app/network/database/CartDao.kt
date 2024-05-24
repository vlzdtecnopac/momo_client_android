package com.momocoffe.app.network.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(product: Cart)

    @Query("SELECT * FROM cart")
    fun getAllCart(): Flow<List<Cart>>

    @Delete
    suspend fun deleteCart(product: Cart)
}