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
fun ListsScreen(db: ShoppingDatabase, onListClickCallback: (Int, String) -> Unit, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var lists by remember { mutableStateOf(emptyList<com.example.shoppinglist.database.ShoppingList>()) }
    var showDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember {mutableStateOf(false)}
    var editingList by remember { mutableStateOf<ShoppingList?>(null) }

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
            onListClick = {list -> onListClickCallback(list.id, list.listName)},
            onDeleteClick = { list ->
                scope.launch {
                    db.shoppingListDao().delete(list)
                    lists = db.shoppingListDao().getAll()
                }
            },
            onEditClick = { list ->
                editingList = list
                showEditDialog = true },
            modifier = Modifier.padding(top = 84.dp)
        )

        PlusButton(
            modifier = Modifier.padding(start = 16.dp, top = 40.dp),
            text = "Add a new list",
            onClick = {
                showDialog = true
            }
        )
    }
    if (showDialog) {
        Dialog(
            title = "New Shopping List",
            textFieldLabel = "List name",
            placeholderText = "List",
            onDismiss = { showDialog = false },
            onConfirm = { listName ->
                scope.launch {
                    val newList = com.example.shoppinglist.database.ShoppingList(listName = listName)
                    db.shoppingListDao().insert(newList)
                    lists = db.shoppingListDao().getAll()
                    showDialog = false
                }
            }
        )
    }

    if (showEditDialog && editingList != null) {
        Dialog(
            title = "Edit Shopping List",
            textFieldLabel = "List name",
            placeholderText = editingList!!.listName,
            onDismiss = { showEditDialog = false },
            onConfirm = { newName ->
                scope.launch {
                    val updatedList = editingList!!.copy(listName = newName)
                    db.shoppingListDao().updateShoppingList(updatedList)
                    lists = db.shoppingListDao().getAll()
                    showEditDialog = false
                    editingList = null
                }
            }
        )
    }
}

