package com.example.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GoodDao {
    @Insert
    fun insert(vararg good: Good)

    @Delete
    fun delete(good: Good)

    @Query("SELECT * FROM good WHERE listId = :listId")
    fun getGoodsForList(listId: Int): List<Good>

    @Update
    fun updateGood(vararg good: Good)

    @Query("UPDATE good SET isBought = 1 WHERE id = :goodId")
    fun markAsBought(goodId: Int)
}

@Dao
interface ShoppingListDao {
    @Insert
    fun insert(vararg shoppingList: ShoppingList)

    @Delete
    fun delete(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list")
    fun getAll(): List<ShoppingList>

    @Update
    fun updateShoppingList(vararg shoppingList: ShoppingList)
}
