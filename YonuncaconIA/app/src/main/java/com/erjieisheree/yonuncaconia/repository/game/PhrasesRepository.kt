package com.erjieisheree.yonuncaconia.repository.game

import com.erjieisheree.yonuncaconia.service.IaService
import com.erjieisheree.yonuncaconia.viewmodel.game.GameCustomizeViewModel

class PhrasesRepository(
    private val iaApi: IaService
) {
    fun fetchPhrases(settings: GameCustomizeViewModel): List<String> {
        val topic = if (settings.usePredefinedTopics) {
            settings.predefinedTopic
        } else {
            settings.userTopic
        }
        return iaApi.getPhrases(topic, settings.gameStyle, settings.cardsQty)
    }
}