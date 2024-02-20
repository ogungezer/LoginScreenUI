package com.example.loginscreenui.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginscreenui.datastore.UserManagement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val userManagement : UserManagement) : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun setMode(mode: Boolean) {
        viewModelScope.launch {
            userManagement.setMode(mode)
            getMode()
        }
    }

    suspend fun getMode() {
        val mode = userManagement.getMode()
        _uiState.update { it.copy(themeMode = mode) }
        val kontrol = _uiState.value.themeMode
        println("kontrol $kontrol")
    }


}