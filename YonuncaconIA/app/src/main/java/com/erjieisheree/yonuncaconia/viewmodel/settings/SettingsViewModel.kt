package com.erjieisheree.yonuncaconia.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erjieisheree.yonuncaconia.data.DataStoreManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    val darkTheme: StateFlow<Boolean> = dataStoreManager.dataFlow
        .map { it.darkTheme }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val musicVolume: StateFlow<Float> = dataStoreManager.dataFlow
        .map { it.musicVolume }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.5f)

    val soundVolume: StateFlow<Float> = dataStoreManager.dataFlow
        .map { it.soundVolume }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.5f)

    val vibration: StateFlow<Boolean> = dataStoreManager.dataFlow
        .map { it.vibration }
        .stateIn(viewModelScope, SharingStarted.Lazily, true)

    fun setDarkTheme(darkTheme: Boolean) {
        viewModelScope.launch {
            dataStoreManager.saveDarkTheme(darkTheme)
        }
    }

    fun setMusicVolume(musicVolume: Float) {
        viewModelScope.launch {
            dataStoreManager.saveMusicVol(musicVolume)
        }
    }

    fun setSoundVolume(soundVolume: Float) {
        viewModelScope.launch {
            dataStoreManager.saveSoundVol(soundVolume)
        }
    }

    fun setVibration(vibration: Boolean) {
        viewModelScope.launch {
            dataStoreManager.saveVibration(vibration)
        }
    }
}