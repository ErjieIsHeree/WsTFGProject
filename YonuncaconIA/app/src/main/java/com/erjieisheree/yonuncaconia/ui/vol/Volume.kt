package com.erjieisheree.yonuncaconia.ui.vol

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel

@Composable
fun buttonClickSound(context: Context, settingsViewModel: SettingsViewModel, soundId: Int): MediaPlayer? {
    val soundsVolume by settingsViewModel.soundVolume.collectAsState()
    val buttonClickSound = remember {
        MediaPlayer.create(context, soundId).apply {
            setVolume(soundsVolume, soundsVolume)
        }
    }
    return buttonClickSound
}

@Composable
fun music(context: Context, settingsViewModel: SettingsViewModel, soundId: Int): MediaPlayer? {
    val musicVolume by settingsViewModel.musicVolume.collectAsState()
    val buttonClickSound = remember {
        MediaPlayer.create(context, soundId).apply {
            isLooping = true
            setVolume(musicVolume, musicVolume)
        }
    }
    return buttonClickSound
}