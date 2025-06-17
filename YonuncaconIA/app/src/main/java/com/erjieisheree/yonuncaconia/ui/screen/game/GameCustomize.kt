package com.erjieisheree.yonuncaconia.ui.screen.game

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.GeneralBackground
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBackButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomCheckbox
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomTextField
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomTextFieldDropdownMenu
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomButton
import com.erjieisheree.yonuncaconia.ui.vol.buttonClickSound
import com.erjieisheree.yonuncaconia.ui.vol.music
import com.erjieisheree.yonuncaconia.viewmodel.game.GameCustomizeViewModel
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel

@Composable
fun GameCustomizeScreen(
    backToHome: () -> Unit,
    loadGame: () -> Unit,
    gameSettings: GameCustomizeViewModel,
    settingsViewModel: SettingsViewModel
) {
    val context = LocalContext.current

    val startGameBtnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.start_game_click
    )
    val backBtnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.back_button_click
    )

    val customizingMusic = music(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.lobby_music
    )

    LaunchedEffect(Unit) {
        customizingMusic?.isLooping = true
        customizingMusic?.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            customizingMusic?.stop()
            customizingMusic?.release()
        }
    }

    val userTopic = gameSettings.userTopic
    val predefinedTopic = gameSettings.predefinedTopic
    val gameStyle = gameSettings.gameStyle
    val cardsQty = gameSettings.cardsQty
    val usePredefinedTopics = gameSettings.usePredefinedTopics

    val topicsList = listOf(
        stringResource(R.string.topic1),
        stringResource(R.string.topic2),
        stringResource(R.string.topic3)
    )
    val gameStyleList = listOf(
        stringResource(R.string.game_style1),
        stringResource(R.string.game_style2),
        stringResource(R.string.game_style3),
        stringResource(R.string.game_style4)
    )
    val cardsQtyList = listOf("5", "10", "15", "20", "25")


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

    //Title
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp, 100.dp, 35.dp, 50.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(70.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var tried by remember { mutableStateOf(false) }
        var correctTopic by remember { mutableStateOf(false) }
        
        //Screen title
        Text(
            text = stringResource(R.string.title_game_customize),
            modifier = Modifier,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            //User Topic
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.txt_theme)
                )
                CustomTextField(
                    value = userTopic,
                    onValueChange = {
                        gameSettings.updateUserTopic(it)
                        correctTopic = validateTheme(it, true, context) == null
                        tried = false
                                    },
                    label = stringResource(R.string.lbl_theme),
                    validation = { validateTheme(it, tried, context) },
                    hide = false,
                    trailingIcon = null
                )
            }

            //Predefined Topics
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.txt_predefined_topics)
                )
                CustomTextFieldDropdownMenu(
                    selectedOption = predefinedTopic,
                    onValueChange = { gameSettings.updatePredefinedTopic(it) },
                    modifier = Modifier,
                    label = stringResource(R.string.lbl_predefined_topics),
                    optionList = topicsList
                )
            }

            //Predefined Styles
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.txt_game_style)
                )
                CustomTextFieldDropdownMenu(
                    selectedOption = gameStyle,
                    onValueChange = { gameSettings.updateGameStyle(it) },
                    modifier = Modifier,
                    label = stringResource(R.string.lbl_game_style),
                    optionList = gameStyleList,
                )
            }

            //Cards Quantity
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelSmall,
                    text = stringResource(R.string.txt_cards_qty)
                )
                CustomTextFieldDropdownMenu(
                    selectedOption = cardsQty,
                    onValueChange = { gameSettings.updateCardsQty(it) },
                    modifier = Modifier
                        .width(86.dp)
                    ,
                    label = stringResource(R.string.lbl_empty),
                    optionList = cardsQtyList,
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
        ) {
            //Use Predefined Topics
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelSmall,
                    text = stringResource(R.string.txt_use_predefined)
                )
                CustomCheckbox(
                    usePredefinedTopics,
                    onCheckedChange = { gameSettings.updateUsePredefinedTopics(it) },
                    modifier = Modifier
                )
            }


            //Start Game Button
            CustomButton(
                title = stringResource(R.string.play),
                onClick = {
                    startGameBtnClickSnd?.start()
                    tried = true
                    if (correctTopic || usePredefinedTopics) loadGame()
                },
                modifier = Modifier
                    .height(80.dp)
                    .width(180.dp)
            )
        }
    }
}

fun validateTheme(theme: String, tried: Boolean, context: Context): String? {
    val regex = Regex("^[A-Za-zÑñÁÉÍÓÚáéíóúÜü ]+$")

    return when {
        !tried -> null
        theme.isBlank() -> context.getString(R.string.error_empty)
        theme.length < 3 -> context.getString(R.string.error_less_than_three)
        theme.length > 20 -> context.getString(R.string.error_more_than_twenty)
        !regex.matches(theme) -> context.getString(R.string.error_invalid)
        else -> null
    }
}