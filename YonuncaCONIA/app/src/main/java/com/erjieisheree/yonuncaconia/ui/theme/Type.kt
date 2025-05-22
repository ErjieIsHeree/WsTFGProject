package com.erjieisheree.yonuncaconia.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.erjieisheree.yonuncaconia.R

private val myFont = FontFamily(
    Font(R.font.inter, FontWeight.Thin),
    Font(R.font.jockeyone, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    //El titulo inicial
    titleLarge = TextStyle(
        fontFamily = myFont,
        fontWeight = FontWeight.Bold,
        fontSize = 90.sp,
        lineHeight = 80.sp
    ),
    //El Tap Tap...
    labelSmall = TextStyle(
        fontFamily = myFont,
        fontWeight = FontWeight.Thin,
        fontSize = 25.sp
    ),
    //TODO Labels medianos
    labelMedium = TextStyle(
        fontFamily = myFont,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    //TODO El titulo de las cartas
    titleMedium = TextStyle(
        fontFamily = myFont,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    //TODO Las cartas del juego
    bodyLarge = TextStyle(
        fontFamily = myFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    //TODO Labels grandes
    labelLarge = TextStyle(
        fontFamily = myFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    //TODO Favoritos
    bodySmall = TextStyle(
        fontFamily = myFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
)