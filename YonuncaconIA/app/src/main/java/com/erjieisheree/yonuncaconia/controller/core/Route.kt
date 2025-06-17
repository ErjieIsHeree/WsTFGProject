package com.erjieisheree.yonuncaconia.controller.core

sealed class Route(val route: String) {
    data object Main : Route("Main")
    data object Customize : Route("Customize")
    data object Load : Route("GameLoadSplash")
    data object Game : Route("Game")
}