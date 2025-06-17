package com.erjieisheree.yonuncaconia.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.erjieisheree.yonuncaconia.ui.theme.Transparent
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel

@Composable
fun MenuBlock(
    closeSettings: () -> Unit,
    darkTheme: (Boolean) -> Unit,
    settingsViewModel: SettingsViewModel
) {

    var showBlock by remember { mutableIntStateOf(0) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Transparent)
            .clickable { closeSettings()  }
        ,
        contentAlignment = Alignment.Center
    ) {
        when (showBlock) {
            0 -> ConfigurationsMenu(
                showSettings = { showBlock = 1 },
                showThemes = { showBlock = 2 },
                settingsViewModel = settingsViewModel
            )
            1 -> SettingsMenu(
                back = { showBlock = 0 },
                settingsViewModel = settingsViewModel
            )
            2 -> ThemeMenu(
                back = { showBlock = 0 },
                darkTheme = darkTheme,
                settingsViewModel = settingsViewModel
            )
        }
    }
}