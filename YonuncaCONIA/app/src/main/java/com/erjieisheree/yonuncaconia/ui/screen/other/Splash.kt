package com.erjieisheree.yonuncaconia.ui.screen.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import kotlinx.coroutines.delay

@Composable
fun InitSplashScreen(navigateToHome: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        navigateToHome()
    }

    GeneralBackground(true)
    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        SplashContent()
    }
}

@Composable
fun SplashContent() {
    Text(
        text = stringResource(R.string.title_game),
        modifier = Modifier
            .rotate(15f)
        ,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayLarge
    )
    Text(
        text = stringResource(R.string.lbl_loading),
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.displaySmall
    )
}