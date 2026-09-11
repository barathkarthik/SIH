package com.example.sih.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val Scheme = lightColorScheme(
    primary = Accent,
    onPrimary = Page,
    background = Page,
    onBackground = Ink,
    surface = Page,
    onSurface = Ink,
    surfaceVariant = SurfaceGrey,
    onSurfaceVariant = Secondary,
    outline = Hairline,
    error = AlertRed,
)

/**
 * Light only, by design. The app is used outdoors in bright sun and dark mode
 * would hurt legibility, so [isSystemInDarkTheme] is deliberately ignored.
 */
@Composable
fun ClimoraTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Page.toArgb()
            window.navigationBarColor = Page.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = true
                isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = Scheme,
        typography = ClimoraTypography,
        content = content,
    )
}