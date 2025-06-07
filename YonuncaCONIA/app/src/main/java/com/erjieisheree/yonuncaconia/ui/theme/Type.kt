package com.erjieisheree.yonuncaconia.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.erjieisheree.yonuncaconia.R

private val JockeyOne = FontFamily(Font(R.font.jockeyone))
private val Inter = FontFamily(Font(R.font.inter))

// Set of Material typography styles to start with
val Typography = Typography(
    //Splash Screen
    displayLarge = TextStyle(
        fontFamily = JockeyOne,
        fontWeight = FontWeight.Normal,
        fontSize = 85.sp,
        lineHeight = 75.sp,
        letterSpacing = (-0.4).sp
    ),
    displaySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp
    ),
    //Card title
    displayMedium = TextStyle(
        fontFamily = JockeyOne,
        fontWeight = FontWeight.Normal,
        fontSize = 56.sp,
        letterSpacing = (-0.5).sp //TODO
    ),
    //Card text
    labelLarge = TextStyle(
        fontFamily = JockeyOne,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    //Play buttons and main titles
    titleLarge = TextStyle(
        fontFamily = JockeyOne,
        fontWeight = FontWeight.Normal,
        fontSize = 46.sp
    ),
    //General text
    labelMedium = TextStyle(
        fontFamily = JockeyOne,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    //Small text
    labelSmall = TextStyle(
        fontFamily = JockeyOne,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
)