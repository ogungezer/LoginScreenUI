package com.example.loginscreenui.ui.theme

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import com.example.loginscreenui.LocalAppDimens
import com.example.loginscreenui.MainActivity
import com.example.loginscreenui.Utils
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E293B),
    surface = Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFBFDBFE),
    surface = Color.White,
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LoginScreenUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    activity: Activity = LocalContext.current as MainActivity,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    isSwitchDark : Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSwitchDark) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isSwitchDark
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(if(isSwitchDark) Black else Color.White)

    val window = calculateWindowSizeClass(activity = activity)
    val config = LocalConfiguration.current

    var typography = CompactTypography
    var appDimens = CompactDimens

    when(window.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            if(config.screenWidthDp <= 360){
                appDimens = CompactSmallDimens
                typography = CompactSmallTypography
            }
            else if(config.screenWidthDp < 599){
                appDimens = CompactMediumDimens
                typography = CompactMediumTypography
            }
            else{
                appDimens = CompactDimens
                typography = CompactTypography
            }
        }
        WindowWidthSizeClass.Medium -> {
            appDimens = MediumDimens
            typography = MediumTypography
        }
        else -> {
            appDimens = ExpandedDimens
            typography = ExpandedTypography
        }
    }

    Utils(appDimens = appDimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

val MaterialTheme.dimens
    @Composable
    get() = LocalAppDimens.current