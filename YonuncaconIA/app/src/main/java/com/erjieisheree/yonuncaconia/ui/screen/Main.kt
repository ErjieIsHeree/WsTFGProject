package com.erjieisheree.yonuncaconia.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.setting.MenuBlock
import com.erjieisheree.yonuncaconia.ui.screen.user.LoginScreen
import com.erjieisheree.yonuncaconia.ui.screen.user.ProfileScreen
import com.erjieisheree.yonuncaconia.ui.screen.user.SignUpScreen
import com.erjieisheree.yonuncaconia.ui.vol.buttonClickSound
import com.erjieisheree.yonuncaconia.ui.vol.music
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel
import com.erjieisheree.yonuncaconia.viewmodel.user.UserViewModel

@Composable
fun MainScreen(
    darkTheme: (Boolean) -> Unit,
    navigateToGameCustomize: () -> Unit,
    userViewModel: UserViewModel,
    settingsViewModel: SettingsViewModel
) {
    val context = LocalContext.current
    val menuBtnClickSnd = buttonClickSound(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.bottom_bar_click
    )

    val lobbyMusic = music(
        context = context,
        settingsViewModel = settingsViewModel,
        soundId = R.raw.lobby_music
    )

    LaunchedEffect(Unit) {
        lobbyMusic?.isLooping = true
        lobbyMusic?.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            lobbyMusic?.stop()
            lobbyMusic?.release()
        }
    }

    val navItemList = listOf(
        MainNavItem(stringResource(R.string.desc_user), Icons.Outlined.Person),
        MainNavItem(stringResource(R.string.desc_home), Icons.Outlined.Home),
        MainNavItem(stringResource(R.string.desc_game), Icons.Outlined.PlayArrow)
    )

    var selectedItem by remember { mutableIntStateOf(1) }

    var showSettingsBlock by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
        ,
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(120.dp)
                ,
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            menuBtnClickSnd?.start()
                            selectedItem = index
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.label,
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp)
                            )
                        },
                        enabled = !showSettingsBlock,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Body(
                selectedIndex = selectedItem,
                navigateToGame = navigateToGameCustomize,
                showSettingsBlock = { showSettingsBlock = !showSettingsBlock },
                userViewModel = userViewModel,
                settingsViewModel = settingsViewModel
            )
        }
    }

    if (showSettingsBlock)
        MenuBlock(
            closeSettings = { showSettingsBlock = !showSettingsBlock },
            darkTheme = darkTheme,
            settingsViewModel = settingsViewModel
        )
}

@Composable
fun Body(
    selectedIndex: Int,
    navigateToGame: () -> Unit,
    showSettingsBlock: () -> Unit,
    userViewModel: UserViewModel,
    settingsViewModel: SettingsViewModel
) {
    when(selectedIndex){
        0 -> UserScreen(
            userViewModel = userViewModel
        )
        1 -> HomeScreen(
            navigateToGame = navigateToGame,
            showBlock = showSettingsBlock,
            settingsViewModel = settingsViewModel
        )
        2 -> navigateToGame()
    }
}

@Composable
fun UserScreen(
    userViewModel: UserViewModel
) {
    var localIndex by remember { mutableIntStateOf(0) }
    val userId by userViewModel.userId.collectAsState()
    if (userId != "0") localIndex = 1

    when (localIndex) {
        0 -> LoginScreen(
            navigateToSignUp = { localIndex = 2 },
            navigateToProfile = { localIndex = 1 },
            userViewModel = userViewModel
        )
        1 -> ProfileScreen(
            navigateToLogin = { localIndex = 0 },
            userViewModel = userViewModel
        )
        2 -> SignUpScreen(
            navigateToLogin = { localIndex = 0 },
            navigateToProfile = { localIndex = 1 },
            userViewModel = userViewModel
        )
    }
}