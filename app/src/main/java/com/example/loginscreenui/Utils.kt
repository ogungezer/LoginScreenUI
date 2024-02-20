package com.example.loginscreenui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.example.loginscreenui.ui.theme.CompactDimens
import com.example.loginscreenui.ui.theme.Dimen

@Composable
fun Utils(
    appDimens: Dimen,
    content : @Composable () -> Unit
) {

    val appDimen = remember {
        appDimens
    }
    CompositionLocalProvider(LocalAppDimens provides appDimen) {
        content()
    }
}

val LocalAppDimens = compositionLocalOf {
    CompactDimens
}