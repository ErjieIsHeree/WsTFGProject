package com.erjieisheree.yonuncaconia.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        val USERNAME_KEY = stringPreferencesKey("user_username")
        val PASSWORD_KEY = stringPreferencesKey("user_password")
        val ID_KEY = stringPreferencesKey("user_id")

        val DARK_THEME_KEY = booleanPreferencesKey("config_dark_theme")
        val MUSIC_VOL_KEY = floatPreferencesKey("config_volume_music")
        val SOUND_VOL_KEY = floatPreferencesKey("config_volume_sound")
        val VIBRATION_KEY = booleanPreferencesKey("config_vibration")
    }

    suspend fun saveUserData(username: String, password: String, id: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
            prefs[PASSWORD_KEY] = password
            prefs[ID_KEY] = id
        }
    }

    suspend fun saveDarkTheme(darkTheme: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_THEME_KEY] = darkTheme
        }
    }

    suspend fun saveMusicVol(musicVol: Float) {
        context.dataStore.edit { prefs ->
            prefs[MUSIC_VOL_KEY] = musicVol
        }
    }

    suspend fun saveSoundVol(soundVol: Float) {
        context.dataStore.edit { prefs ->
            prefs[SOUND_VOL_KEY] = soundVol
        }
    }

    suspend fun saveVibration(vibrationOn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[VIBRATION_KEY] = vibrationOn
        }
    }

    // Leer datos completos (usuario + configuraciones) como Flow
    val dataFlow: Flow<UserAndConfigData> = context.dataStore.data
        .map { prefs ->
            UserAndConfigData(
                username = prefs[USERNAME_KEY] ?: "",
                password = prefs[PASSWORD_KEY] ?: "",
                id = prefs[ID_KEY] ?: "",
                musicVolume = prefs[MUSIC_VOL_KEY] ?: 0.5F,
                soundVolume = prefs[SOUND_VOL_KEY] ?: 0.5F,
                vibration = prefs[VIBRATION_KEY] ?: true,
                darkTheme = prefs[DARK_THEME_KEY] ?: false,
            )
        }
}

// Data class combinada
data class UserAndConfigData(
    val username: String,
    val password: String,
    val id: String,
    val darkTheme: Boolean,
    val musicVolume: Float,
    val soundVolume: Float,
    val vibration: Boolean
)