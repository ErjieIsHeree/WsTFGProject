package com.erjieisheree.yonuncaconia.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Yellow80,
    secondary = Pink80,
    tertiary = PinkOpc,

    onPrimary = White,
    onSecondary = Black,
    background = Cian80,

    surfaceContainerLowest = Transparent
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = Pink40,
    tertiary = NavyMarine40,

    onPrimary = YellowGreen,
    onSecondary = Black,
    background = AlmostBlack40,

    surfaceContainerLowest = Transparent
)

@Composable
fun YoNuncaConIATheme(
    darkTheme: Boolean,
    /*
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    */
    content: @Composable () -> Unit
) {
    /*
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    */
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}