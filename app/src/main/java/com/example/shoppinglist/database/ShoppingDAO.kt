package com.example.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GoodDao {
    @Insert
    suspend fun insert(good: Good)

    @Delete
    suspend fun delete(good: Good)

    @Query("SELECT * FROM good WHERE listId = :listId")
    suspend fun getGoodsForList(listId: Int): List<Good>

    @Update
    suspend fun updateGood(good: Good)

    @Query("UPDATE good SET isBought = 1 WHERE id = :goodId")
    suspend fun markAsBought(goodId: Int)
}

@Dao
interface ShoppingListDao {
    @Insert
    suspend fun insert(shoppingList: ShoppingList)

    @Delete
    suspend fun delete(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list")
    suspend fun getAll(): List<ShoppingList>

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingList)
}
