package com.erjieisheree.yonuncaconia.domain.usecase.game

import com.erjieisheree.yonuncaconia.repository.game.PhrasesRepository
import com.erjieisheree.yonuncaconia.viewmodel.game.GameCustomizeViewModel

class LoadPhrasesUseCase(
    private val repository: PhrasesRepository
) {
    operator fun invoke(settings: GameCustomizeViewModel): List<String> {
        return repository.fetchPhrases(settings)
    }
}