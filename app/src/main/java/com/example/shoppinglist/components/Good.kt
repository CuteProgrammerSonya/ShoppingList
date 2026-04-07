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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.database.Good
import com.example.shoppinglist.ui.theme.LightPurple
import com.example.shoppinglist.ui.theme.DarkPurple
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun Good(good: Good, onBoughtChange: (Boolean) -> Unit = {}, onDeleteClick: () -> Unit = {}){
    var boughtState by remember { mutableStateOf(good.isBought) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = boughtState,
            onCheckedChange = { isBought ->
                boughtState = isBought
                onBoughtChange(isBought)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = LightPurple,
                uncheckedColor = DarkPurple,
                checkmarkColor = DarkPurple
            )
        )
        Text(
            text = good.goodName,
            fontSize = 16.sp,
            color = if (boughtState) LightPurple else DarkPurple,
            modifier = Modifier.weight(1f),
            textDecoration = if (boughtState) TextDecoration.LineThrough else null
        )

        DeleteButton(
            onClick = onDeleteClick
        )
    }
}