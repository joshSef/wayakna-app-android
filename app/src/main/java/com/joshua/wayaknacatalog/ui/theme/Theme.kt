package com.joshua.wayaknacatalog.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = WayaknaGreen,
    onPrimary = WayaknaNight,
    secondary = WayaknaMint,
    onSecondary = WayaknaNight,
    tertiary = WayaknaGreen,
    onTertiary = WayaknaWhite,
    background = WayaknaNight,
    onBackground = WayaknaWhite,
    surface = WayaknaNavy,
    onSurface = WayaknaWhite,
    surfaceVariant = WayaknaInk,
    onSurfaceVariant = WayaknaMint
)

private val LightColorScheme = lightColorScheme(
    primary = WayaknaNavy,
    onPrimary = WayaknaWhite,
    secondary = WayaknaGreen,
    onSecondary = WayaknaWhite,
    tertiary = WayaknaGreen,
    onTertiary = WayaknaNight,
    background = WayaknaMist,
    onBackground = WayaknaInk,
    surface = WayaknaWhite,
    onSurface = WayaknaInk,
    surfaceVariant = WayaknaCard,
    onSurfaceVariant = WayaknaSlate
)

@Composable
fun WayaknaCatalogTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalContext.current as? Activity

    if (view != null) {
        SideEffect {
            view.window.statusBarColor = colorScheme.background.toArgb()
            val insetsController = WindowCompat.getInsetsController(view.window, view.window.decorView)
            insetsController.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
