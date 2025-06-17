package com.erjieisheree.yonuncaconia.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomButton
import com.erjieisheree.yonuncaconia.ui.vol.buttonClickSound
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel


@Composable
fun ConfigurationsMenu(
    showSettings: () -> Unit,
    showThemes: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    val context = LocalContext.current
    val btnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.general_button_click
    )

    Box (
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            .clickable(
                enabled = false,
                onClick = {}
            )
        ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            CustomButton(
                onClick = {
                    showSettings()
                    btnClickSnd?.start()
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(80.dp)
                ,
                title = stringResource(R.string.settings),
                style = MaterialTheme.typography.labelMedium
            )

            CustomButton(
                onClick = {
                    showThemes()
                    btnClickSnd?.start()
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(80.dp)
                ,
                title = stringResource(R.string.themes),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
