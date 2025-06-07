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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.setting.MenuBlock
import com.erjieisheree.yonuncaconia.ui.screen.user.UserScreen

@Composable
fun MainScreen(
    darkTheme: (Boolean) -> Unit,
    navigateToGame: () -> Unit
) {
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
                        onClick = { selectedItem = index },
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
                navigateToGame = navigateToGame,
                showSettingsBlock = { showSettingsBlock = !showSettingsBlock }
            )
        }
    }

    if (showSettingsBlock)
        MenuBlock(
            closeSettings = { showSettingsBlock = !showSettingsBlock },
            darkTheme = darkTheme
        )
}

@Composable
fun Body(
    selectedIndex: Int,
    navigateToGame: () -> Unit,
    showSettingsBlock: () -> Unit
) {

    when(selectedIndex){
        0 -> UserScreen()
        1 -> HomeScreen(
            navigateToGame = navigateToGame,
            showBlock = showSettingsBlock
        )
        2 -> navigateToGame()
    }
}