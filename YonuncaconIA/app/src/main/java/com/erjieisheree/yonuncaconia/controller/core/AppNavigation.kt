package com.erjieisheree.yonuncaconia.controller.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erjieisheree.yonuncaconia.ui.screen.game.GameCustomizeScreen
import com.erjieisheree.yonuncaconia.ui.screen.game.GameScreen
import com.erjieisheree.yonuncaconia.ui.screen.game.GameLoadScreen
import com.erjieisheree.yonuncaconia.ui.screen.MainScreen
import com.erjieisheree.yonuncaconia.viewmodel.game.GameCustomizeViewModel
import com.erjieisheree.yonuncaconia.viewmodel.game.GamePhrasesViewModel
import com.erjieisheree.yonuncaconia.viewmodel.settings.SettingsViewModel
import com.erjieisheree.yonuncaconia.viewmodel.user.UserViewModel


@Composable
fun AppNavigation(
    darkTheme: (Boolean) -> Unit,
    userViewModel: UserViewModel,
    settingsViewModel: SettingsViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Main.route) {

        composable(Route.Main.route) {
            MainScreen(
                darkTheme = darkTheme,
                navigateToGameCustomize = {
                    navController.navigate(Route.Customize.route) {
                        launchSingleTop = true
                    }
                },
                userViewModel = userViewModel,
                settingsViewModel = settingsViewModel
            )
        }

        composable(Route.Customize.route) {
            val gameCustomizeViewModel: GameCustomizeViewModel = viewModel()
            GameCustomizeScreen(
                backToHome = { navController.popBackStack() },
                loadGame = {
                    navController.navigate(Route.Load.route) {
                        launchSingleTop = true
                    }
                },
                gameSettings = gameCustomizeViewModel,
                settingsViewModel = settingsViewModel
            )
        }

        composable(Route.Load.route) {
            val parentEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(Route.Customize.route)
            }
            val gameCustomizeViewModel: GameCustomizeViewModel = viewModel(parentEntry)
            val gamePhrasesViewModel: GamePhrasesViewModel = viewModel()
            GameLoadScreen(
                navigateToGame = {
                    navController.navigate(Route.Game.route) {
                        launchSingleTop = true
                    }
                },
                gameSettings =  gameCustomizeViewModel,
                gamePhrases =  gamePhrasesViewModel
            )
        }

        composable(Route.Game.route) {
            val parentEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(Route.Load.route)
            }
            val gamePhrasesViewModel: GamePhrasesViewModel = viewModel(parentEntry)
            GameScreen(
                backToHome = {
                    navController.navigate(Route.Main.route) {
                        launchSingleTop = true
                        popUpTo(Route.Game.route) { inclusive = true }
                    }
                },
                gamePhrases = gamePhrasesViewModel,
                settingsViewModel = settingsViewModel
            )
        }
    }
}