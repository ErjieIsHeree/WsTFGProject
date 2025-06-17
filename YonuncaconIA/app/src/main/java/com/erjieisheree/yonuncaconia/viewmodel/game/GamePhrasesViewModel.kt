package com.erjieisheree.yonuncaconia.viewmodel.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erjieisheree.yonuncaconia.domain.usecase.game.LoadPhrasesUseCase
import com.erjieisheree.yonuncaconia.repository.game.PhrasesRepository
import com.erjieisheree.yonuncaconia.service.apis.DeepseekApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GamePhrasesViewModel : ViewModel() {
    val loadPhrasesUseCase = LoadPhrasesUseCase(PhrasesRepository(DeepseekApi()))

    private var cardsQty by mutableIntStateOf(0)
    var phrasesList = mutableStateListOf<String>()
        private set

    fun updateCardsQty(newQty: Int) {
        cardsQty = newQty
    }

    fun loadPhrases(settings: GameCustomizeViewModel): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            val phrases = loadPhrasesUseCase(settings)
            phrasesList.clear()
            phrasesList.addAll(phrases)
        }
    }

}
