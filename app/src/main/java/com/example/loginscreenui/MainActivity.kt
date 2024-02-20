package com.example.loginscreenui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginscreenui.presentation.AppViewModel
import com.example.loginscreenui.presentation.LoginScreen
import com.example.loginscreenui.ui.theme.Black
import com.example.loginscreenui.ui.theme.LoginScreenUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : AppViewModel = viewModel()
            val state = viewModel.uiState.collectAsState()
            LoginScreenUITheme(isSwitchDark = (state.value.themeMode == "dark")) {
                LoginScreen()
            }
        }
    }
}



