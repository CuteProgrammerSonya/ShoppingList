package com.example.shoppinglist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.database.ShoppingDatabase
import com.example.shoppinglist.database.ShoppingList
import com.example.shoppinglist.components.*
import kotlinx.coroutines.launch


@Composable
fun ListsScreen(db: ShoppingDatabase, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var lists by remember { mutableStateOf(emptyList<com.example.shoppinglist.database.ShoppingList>()) }

    LaunchedEffect(Unit) {
        lists = db.shoppingListDao().getAll()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ListsLayout(
            shoppingLists = lists,
            onListClick = {list ->
                println("Открыт список: ${list.listName}")},
            onDeleteClick = { list ->
                scope.launch {
                    db.shoppingListDao().delete(list)
                    lists = db.shoppingListDao().getAll()
                }
            },
            modifier = Modifier.padding(top = 84.dp)
        )

        PlusButton(
            modifier = Modifier.padding(start = 16.dp, top = 40.dp),
            onClick = {
                scope.launch {
                    val newList = com.example.shoppinglist.database.ShoppingList(
                        listName = "List ${System.currentTimeMillis()}"
                    )
                    db.shoppingListDao().insert(newList)
                    lists = db.shoppingListDao().getAll()
                }
            }
        )
    }
}

