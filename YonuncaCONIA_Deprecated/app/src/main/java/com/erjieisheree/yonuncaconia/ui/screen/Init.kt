package com.erjieisheree.yonuncaconia.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erjieisheree.yonuncaconia.ui.navigation.AppScreens
import com.erjieisheree.yonuncaconia.ui.others.Background
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaCONIATheme

@Composable
fun InitScreen(navController: NavController) {
    Background()
    InitScreenItems(navController)
}

@Composable
fun InitScreenItems(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column (
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = { navController.navigate("home") }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            text = "Yo nunca... CON IA???",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .rotate(20f)
        )
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall,
            text = "Tap Tap...",
            modifier = Modifier
                .offset(0.dp, screenHeight * 0.05f)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, apiLevel = 34)
@Composable
private fun Preview() {
    YoNuncaCONIATheme {
        InitScreen(rememberNavController())
    }
}
