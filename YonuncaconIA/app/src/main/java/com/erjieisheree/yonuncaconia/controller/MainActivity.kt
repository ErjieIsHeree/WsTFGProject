package com.erjieisheree.yonuncaconia.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.erjieisheree.yonuncaconia.controller.core.AppNavigation
import com.erjieisheree.yonuncaconia.data.DataStoreManager
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaConIATheme
import com.erjieisheree.yonuncaconia.viewmodel.settings.ConfigViewModelFactory
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel
import com.erjieisheree.yonuncaconia.viewmodel.user.UserViewModel
import com.erjieisheree.yonuncaconia.viewmodel.user.UserViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStoreManager = DataStoreManager(this)
        val userFactory = UserViewModelFactory(dataStoreManager)
        userViewModel = ViewModelProvider(this, userFactory)[UserViewModel::class.java]
        val configFactory = ConfigViewModelFactory(dataStoreManager)
        settingsViewModel = ViewModelProvider(this, configFactory)[SettingsViewModel::class.java]

        enableEdgeToEdge()
        setContent {

            val darkTheme by settingsViewModel.darkTheme.collectAsState()

            YoNuncaConIATheme(darkTheme) {
                AppNavigation(
                    darkTheme = { isDark ->
                        settingsViewModel.setDarkTheme(isDark)
                    },
                    userViewModel = userViewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}