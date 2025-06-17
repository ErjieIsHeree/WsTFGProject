package com.erjieisheree.yonuncaconia.viewmodel.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameCustomizeViewModel : ViewModel() {
    var userTopic by mutableStateOf("")
        private set
    var predefinedTopic by mutableStateOf("General")
        private set
    var gameStyle by mutableStateOf("Divertido")
        private set
    var cardsQty by mutableStateOf("5")
        private set
    var usePredefinedTopics by mutableStateOf(false)
        private set

    fun updateUserTopic(topic: String) {
        userTopic = topic
    }

    fun updatePredefinedTopic(topic: String) {
        predefinedTopic = topic
    }

    fun updateGameStyle(style: String) {
        gameStyle = style
    }

    fun updateCardsQty(qty: String) {
        cardsQty = qty
    }

    fun updateUsePredefinedTopics(use: Boolean) {
        usePredefinedTopics = use
    }
}