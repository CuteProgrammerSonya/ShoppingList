package com.example.shoppinglist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.database.Good

@Composable
fun GoodsLayout(
    goods: List<Good>,
    onBoughtChange: (Good, Boolean) -> Unit,
    onDeleteClick: (Good) -> Unit,
    onEditClick: (Good) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
        items(goods) { good ->
            Good(
                good = good,
                onBoughtChange = { isBought -> onBoughtChange(good, isBought) },
                onDeleteClick = { onDeleteClick(good) },
                onEditClick = { onEditClick(good) }
            )
        }
    }
}