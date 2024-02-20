package com.example.loginscreenui.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserManagement @Inject constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
        private val SYSTEM_UI_MODE = stringPreferencesKey("mode")
    }

   suspend fun setMode(mode : Boolean){ //if the mode parameter is true it means the ui is dark. else then ui is light.
        when(mode){
            true -> {
                context.dataStore.edit {
                    it[SYSTEM_UI_MODE] = "dark"
                }
            }
            else -> {
                context.dataStore.edit {
                    it[SYSTEM_UI_MODE] = "light"
                }
            }
        }
    }

    suspend fun getMode() : String {
        val mode = context.dataStore.data.first()
        return mode[SYSTEM_UI_MODE] ?: "light"
    }

}

