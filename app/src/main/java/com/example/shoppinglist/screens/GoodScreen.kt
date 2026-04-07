package com.example.shoppinglist.screens

import androidx.activity.compose.BackHandler
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
import com.example.shoppinglist.database.Good
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
    var showDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false)}
    var editingGood by remember { mutableStateOf<Good?>(null) }

    LaunchedEffect(listId) {
        goods = db.goodDao().getGoodsForList(listId)
    }

    BackHandler {
        onBackClick()
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
            onClick = { showDialog = true }
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
            onEditClick = {good ->
                editingGood = good
                showEditDialog = true},
            modifier = Modifier.weight(1f)
        )
    }
    if (showDialog) {
        Dialog(
            title = "New Good",
            textFieldLabel = "Good name",
            placeholderText = "Good",
            onDismiss = { showDialog = false },
            onConfirm = { goodName ->
                scope.launch {
                    val newGood = com.example.shoppinglist.database.Good(
                        goodName = goodName,
                        listId = listId,
                        isBought = false
                    )
                    db.goodDao().insert(newGood)
                    goods = db.goodDao().getGoodsForList(listId)
                    showDialog = false
                }
            }
        )
    }

    if (showEditDialog && editingGood != null) {
        Dialog(
            title = "Edit Good",
            textFieldLabel = "Good name",
            placeholderText = editingGood!!.goodName,
            onDismiss = { showEditDialog = false },
            onConfirm = { newName ->
                scope.launch {
                    val updatedGood = editingGood!!.copy(goodName = newName)
                    db.goodDao().updateGood(updatedGood)
                    goods = db.goodDao().getGoodsForList(listId)
                    showEditDialog = false
                    editingGood = null
                }
            }
        )
    }
}