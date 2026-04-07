package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.shoppinglist.database.ShoppingList
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.database.ShoppingDatabase
import com.example.shoppinglist.ui.theme.LightPurple
import com.example.shoppinglist.ui.theme.MediumPurple
import com.example.shoppinglist.ui.theme.DarkPurple
import com.example.shoppinglist.screens.ListsScreen
import com.example.shoppinglist.screens.GoodScreen
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue


class MainActivity : ComponentActivity() {
    private lateinit var db: ShoppingDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        db = Room.databaseBuilder(
            applicationContext,
            ShoppingDatabase::class.java,
            "shopping_database"
        ).build()
        setContent {
            var currentListId by remember { mutableStateOf<Int?>(null) }
            var currentListName by remember { mutableStateOf("") }

            if (currentListId == null) {
                ListsScreen(
                    db = db,
                    onListClickCallback = { listId, listName ->
                        currentListId = listId
                        currentListName = listName
                    }
                )
            } else {
                GoodScreen(
                    db = db,
                    listId = currentListId!!,
                    listName = currentListName,
                    onBackClick = {currentListId = null}
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PlusButtonPreview() {
//    ShoppingListTheme {
//        PlusButton()
//    }
//}