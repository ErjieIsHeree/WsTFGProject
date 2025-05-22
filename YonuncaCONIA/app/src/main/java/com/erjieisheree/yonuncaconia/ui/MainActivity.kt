package com.erjieisheree.yonuncaconia.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.erjieisheree.yonuncaconia.ui.navigation.AppNavigation
import com.erjieisheree.yonuncaconia.ui.others.Background
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaCONIATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YoNuncaCONIATheme {
                AppNavigation()
            }
        }
    }
}

@Preview (uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppPreview() {
    YoNuncaCONIATheme {
        AppNavigation()
    }
}