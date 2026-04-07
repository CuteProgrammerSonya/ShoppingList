package com.example.shoppinglist.components
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.example.shoppinglist.ui.theme.DarkPurple
import com.example.shoppinglist.ui.theme.MediumPurple

@Composable
fun Dialog(
    title: String,
    textFieldLabel: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text(textFieldLabel) },
                singleLine = true,
                colors =  OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DarkPurple,
                    unfocusedBorderColor = MediumPurple,
                    focusedLabelColor = DarkPurple,
                    unfocusedLabelColor = MediumPurple
                )
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        onConfirm(inputText)
                        inputText = ""
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = DarkPurple
                )
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    inputText = ""
                    onDismiss()
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = DarkPurple
                )
            ) {
                Text("Cancel")
            }
        },
        containerColor = Color.White,
        titleContentColor = DarkPurple,
        textContentColor = DarkPurple
    )
}