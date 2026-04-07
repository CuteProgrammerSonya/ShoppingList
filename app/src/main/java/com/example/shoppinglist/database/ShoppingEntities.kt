package com.example.shoppinglist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(tableName = "good",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingList::class,
            parentColumns = ["id"],
            childColumns = ["listId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("listId")]
)
data class Good(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val goodName: String,
    val isBought: Boolean = false,
    val listId: Int
)

@Entity(tableName = "shopping_list")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val listName: String,
    val createdAt: Long = System.currentTimeMillis()
)
