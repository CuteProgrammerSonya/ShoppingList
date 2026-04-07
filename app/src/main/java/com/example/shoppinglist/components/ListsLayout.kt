package com.example.shoppinglist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.database.ShoppingList

@Composable
fun ListsLayout(
    shoppingLists: List<ShoppingList>,
    onListClick: (ShoppingList) -> Unit,
    onDeleteClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(shoppingLists) { list ->
            ListBox(
                list = list,
                onClick = { onListClick(list) },
                onDeleteClick = { onDeleteClick(list) },
                onEditClick = { onEditClick(list) }
            )
        }
    }
}