package com.erjieisheree.yonuncaconia.ui.screen.game

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
import com.erjieisheree.yonuncaconia.ui.screen.other.GeneralBackground
import com.erjieisheree.yonuncaconia.viewmodel.game.GameCustomizeViewModel
import com.erjieisheree.yonuncaconia.viewmodel.game.GamePhrasesViewModel

@Composable
fun GameLoadScreen(
    navigateToGame: () -> Unit,
    gameSettings: GameCustomizeViewModel,
    gamePhrases: GamePhrasesViewModel
) {
    LaunchedEffect(Unit) {
        gamePhrases.updateCardsQty(gameSettings.cardsQty.toInt())
        val job = gamePhrases.loadPhrases(gameSettings)
        job.join()
        navigateToGame()
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