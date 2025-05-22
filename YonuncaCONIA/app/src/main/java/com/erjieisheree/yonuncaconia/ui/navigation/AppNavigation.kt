package com.erjieisheree.yonuncaconia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erjieisheree.yonuncaconia.ui.screen.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(route = AppScreens.FirstScreen.route) {
            InitScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.ThirdScreen.route) {
            PregameScreen(navController)
        }
        composable(route = AppScreens.FourthScreen.route) {
            GameScreen(navController)
        }
    }
}