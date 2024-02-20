package com.example.loginscreenui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.loginscreenui.presentation.AppViewModel
import com.example.loginscreenui.ui.theme.Black
import com.example.loginscreenui.ui.theme.BlueGray
import com.example.loginscreenui.ui.theme.LightBlueWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    label: String,
    trailing: String,
    modifier: Modifier,
    viewModel: AppViewModel
) {
    var value by rememberSaveable { mutableStateOf("")}
    val uiState = viewModel.uiState.collectAsState()
    val uiColor = if (uiState.value.themeMode == "dark") Color.White else Black
    TextField(
        value = value,
        onValueChange = {value = it},
        modifier = modifier,
        label = { Text(text = label, style = MaterialTheme.typography.labelMedium, color = uiColor.copy(alpha = 0.7f)) },
        trailingIcon = {
            if(trailing.isNotBlank()){
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = trailing, color = uiColor, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                }
            }
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = if(uiState.value.themeMode == "dark") Color.White else Color.Black,
            disabledTextColor = if(uiState.value.themeMode == "dark") Color(0xFF94A3B8) else Color(0xFF475569),
            unfocusedContainerColor = if(uiState.value.themeMode == "dark") BlueGray.copy(alpha = 0.6f) else LightBlueWhite ,
            focusedContainerColor = if(uiState.value.themeMode == "dark") BlueGray.copy(alpha = 0.6f) else LightBlueWhite
        )
    )

}