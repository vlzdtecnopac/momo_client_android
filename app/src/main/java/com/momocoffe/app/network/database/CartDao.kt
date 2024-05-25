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
    suspend fun deleteProduct(product: Cart)

    @Query("UPDATE cart SET count_product = :count, price_product_mod = :price WHERE id = :id")
    suspend fun updateProductCountById(id: Int, count: Int, price: Int)

}