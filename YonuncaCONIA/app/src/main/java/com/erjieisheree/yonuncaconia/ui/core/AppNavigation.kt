package com.erjieisheree.yonuncaconia.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erjieisheree.yonuncaconia.ui.screen.game.GameCustomizeScreen
import com.erjieisheree.yonuncaconia.ui.screen.game.GameScreen
import com.erjieisheree.yonuncaconia.ui.screen.other.InitSplashScreen
import com.erjieisheree.yonuncaconia.ui.screen.MainScreen


@Composable
fun AppNavigation(
    darkTheme: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = InitSplash) {
        composable<InitSplash> {
            InitSplashScreen { navController.navigate(Main) }
        }

        composable<Main> {
            MainScreen(darkTheme) { navController.navigate(Customize) }
        }

        composable<Customize> {
            GameCustomizeScreen(
                backToHome = { navController.navigate(Main) },
                loadGame = { navController.navigate(GameLoadSplash) },
                gameStart = { navController.navigate(Game) }
            )
        }

        composable<GameLoadSplash> {
            /* TODO */
        }

        composable<Game> {
            GameScreen { navController.navigate(Main) }
        }
    }
}