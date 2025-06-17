package com.erjieisheree.yonuncaconia.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBackButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomCheckbox
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomSlider
import com.erjieisheree.yonuncaconia.ui.vol.buttonClickSound
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel

@Composable
fun SettingsMenu(
    back: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    val context = LocalContext.current
    val backBtnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.back_button_click
    )

    val musicVolume by settingsViewModel.musicVolume.collectAsState()
    val soundVolume by settingsViewModel.soundVolume.collectAsState()
    val vibration by settingsViewModel.vibration.collectAsState()

    Column (
        modifier = Modifier
            .padding(20.dp)
            .background(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            .fillMaxWidth()
            .clickable(
                enabled = false,
                onClick = {}
            )
        ,
        verticalArrangement = Arrangement.Center
    ) {
        CustomBackButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
                backBtnClickSnd?.start()
                back()
            }
        )

        Column (
            modifier = Modifier
                .padding(20.dp, 0.dp, 20.dp, 20.dp)
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                .fillMaxWidth()
            ,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                ,
                verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.music),
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )

                    CustomSlider(
                        modifier = Modifier,
                        value = musicVolume,
                        onValueChange = { settingsViewModel.setMusicVolume(it) }
                    )
                }

                Column {
                    Text(
                        text = stringResource(R.string.sounds),
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )

                    CustomSlider(
                        modifier = Modifier,
                        value = soundVolume,
                        onValueChange = { settingsViewModel.setSoundVolume(it) }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.vibration),
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomCheckbox(
                            checked = vibration,
                            onCheckedChange = { settingsViewModel.setVibration(it) },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}