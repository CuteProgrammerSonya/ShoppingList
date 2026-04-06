package com.example.shoppinglist.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.database.ShoppingList
import com.example.shoppinglist.ui.theme.LightPurple
import com.example.shoppinglist.ui.theme.DarkPurple
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ListBox(list: ShoppingList, onClick: () -> Unit = {}, onDeleteClick: () -> Unit = {}){
    val dateFormat = SimpleDateFormat("dd.mm.yyyy hh:mm", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(list.createdAt))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightPurple
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = list.listName,
                    style = MaterialTheme.typography.titleLarge,
                    color = DarkPurple
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkPurple
                )
            }

            DeleteButton(
                onClick = onDeleteClick
            )
        }
    }
}
