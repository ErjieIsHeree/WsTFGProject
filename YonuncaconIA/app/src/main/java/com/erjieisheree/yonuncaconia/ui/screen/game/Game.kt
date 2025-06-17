package com.erjieisheree.yonuncaconia.ui.screen.game

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.GeneralBackground
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBackButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBtn
import com.erjieisheree.yonuncaconia.ui.vol.buttonClickSound
import com.erjieisheree.yonuncaconia.ui.vol.music
import com.erjieisheree.yonuncaconia.viewmodel.game.GamePhrasesViewModel
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun GameScreen(
    backToHome: () -> Unit,
    gamePhrases: GamePhrasesViewModel,
    settingsViewModel: SettingsViewModel
) {
    val context = LocalContext.current

    val backBtnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.back_button_click
    )

    val gameMusic = music(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.game_music
    )

    LaunchedEffect(Unit) {
        gameMusic?.isLooping = true
        gameMusic?.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            gameMusic?.stop()
            gameMusic?.release()
        }
    }

    val phrasesList = gamePhrases.phrasesList

    GeneralBackground(false)

    //Back button
    CustomBackButton(
        modifier = Modifier
            .padding(30.dp, 50.dp, 0.dp, 0.dp)
        ,
        onClick = {
            backBtnClickSnd?.start()
            backToHome()
        }
    )

    phrasesList.forEach{ phrase ->
        Card(
            phrase = phrase,
            settingsViewModel = settingsViewModel,
            context = context
        )
    }
}

@Composable
private fun Card(
    phrase: String,
    settingsViewModel: SettingsViewModel,
    context: Context
) {
    val likeBtnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.like
    )

    val dislikeBtnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.dislike
    )

    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var showCard by remember { mutableStateOf(true) }

    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(durationMillis = 600),
        label = "x"
    )
    val animatedOffsetY by animateFloatAsState(
        targetValue = offsetY,
        animationSpec = tween(durationMillis = 600),
        label = "y",
    )

    val coroutineScope = rememberCoroutineScope()

    if (showCard) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(37.dp, 150.dp, 37.dp, 70.dp)
                .clickable(
                    enabled = false,
                    onClick = {}
                )
                .offset {
                    IntOffset(
                        animatedOffsetX.roundToInt(),
                        animatedOffsetY.roundToInt()
                    )
                }
            ,
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = stringResource(R.string.title_card),
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(
                    modifier = Modifier
                        .weight(3f)
                )
                Text(
                    text = phrase,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(
                    modifier = Modifier
                        .weight(3f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    CustomBtn(
                        onClick = {
                            dislikeBtnClickSnd?.start()
                            offsetX += -1500f
                            offsetY += 250f
                            coroutineScope.launch {
                                delay(300)
                                showCard = false
                            }
                        },
                        paintId = R.drawable.cross
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    CustomBtn(
                        onClick = {
                            likeBtnClickSnd?.start()
                            offsetX += 1500f
                            offsetY += 250f
                            coroutineScope.launch {
                                delay(300)
                                showCard = false
                            }
                        },
                        paintId = R.drawable.tick
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}