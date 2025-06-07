package com.erjieisheree.yonuncaconia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.erjieisheree.yonuncaconia.ui.core.AppNavigation
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaConIATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            YoNuncaConIATheme(darkTheme) {
                AppNavigation(
                    darkTheme = { darkTheme = it }
                )
            }
        }
    }
}