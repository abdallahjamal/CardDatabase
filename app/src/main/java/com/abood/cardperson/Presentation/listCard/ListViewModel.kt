package com.abood.cardperson.Presentation.listCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abood.cardperson.domain.model.Cards
import com.abood.cardperson.data.repository.CardsRepository
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val cardRepository = CardsRepository

    // LiveData لمراقبة جميع البطاقات
    private val _allCards = MutableLiveData<List<Cards>>(emptyList())
    val allCards: LiveData<List<Cards>> get() = _allCards

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            cardRepository.getAllCards().observeForever { cards ->
                _allCards.postValue(cards)
            }
        }
    }

    fun getCount(): Int {
        var count = 0
        viewModelScope.launch {
            count = cardRepository.getCardsCount()
        }
        return count
    }


    fun deleteCards(cards: Cards) {
        viewModelScope.launch {
            cardRepository.deleteCards(cards)
            loadCards()
        }
    }


    fun getById(id: Int): Cards {
        var card = Cards(0, "", "", "", "", 0)
        viewModelScope.launch {
            card = cardRepository.getCardsById(id)
        }
        return card
    }

    fun updateCardsList() {
        viewModelScope.launch {
            _allCards.postValue(cardRepository.getAllCards().value ?: emptyList())
        }
    }
}
