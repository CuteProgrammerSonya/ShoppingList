package com.example.shoppinglist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.database.ShoppingDatabase
import com.example.shoppinglist.database.ShoppingList
import com.example.shoppinglist.components.*
import com.example.shoppinglist.ui.theme.DarkPurple
import com.example.shoppinglist.ui.theme.MediumPurple
import kotlinx.coroutines.launch


@Composable
fun GoodScreen(
    db: ShoppingDatabase,
    listId: Int,
    listName: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var goods by remember { mutableStateOf(emptyList<com.example.shoppinglist.database.Good>()) }

    LaunchedEffect(listId) {
        goods = db.goodDao().getGoodsForList(listId)
    }

    Column(
        modifier = modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Back",
            fontSize = 18.sp,
            color = MediumPurple,
            modifier = Modifier.padding(start = 16.dp, top = 50.dp).clickable{onBackClick()}
        )
        Text(
            text = listName,
            fontSize = 24.sp,
            color = DarkPurple,
            modifier = Modifier.padding(start = 16.dp)
        )

        PlusButton(
            modifier = Modifier.padding(start = 16.dp),
            text = "Add a new good",
            onClick = {
                scope.launch {
                    val newGood = com.example.shoppinglist.database.Good(
                        goodName = "New Good",
                        listId = listId,
                        isBought = false
                    )
                    db.goodDao().insert(newGood)
                    goods = db.goodDao().getGoodsForList(listId)
                }
            }
        )
        GoodsLayout(
            goods = goods,
            onBoughtChange = {good, isBought ->
                scope.launch {
                    db.goodDao().markAsBought(good.id, isBought)
                    goods = db.goodDao().getGoodsForList(listId)
                }
            },
            onDeleteClick = { good ->
                scope.launch {
                    db.goodDao().delete(good)
                    goods = db.goodDao().getGoodsForList(listId)
                }
            },
            modifier = Modifier.weight(1f)
        )
    }
}