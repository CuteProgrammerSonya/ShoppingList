package com.example.shoppinglist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Good::class, ShoppingList::class], version = 2, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun goodDao(): GoodDao
    abstract fun shoppingListDao(): ShoppingListDao
}
