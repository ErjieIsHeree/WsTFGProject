package com.erjieisheree.yonuncaconia.ui.navigation

sealed class AppScreens(val route: String) {
    object FirstScreen: AppScreens("init")
    object SecondScreen: AppScreens("home")
    object ThirdScreen: AppScreens("pregame")
    object FourthScreen: AppScreens("game")
}