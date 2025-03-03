package com.abood.cardperson.Presentation.addCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abood.cardperson.domain.model.Cards
import com.abood.cardperson.data.repository.CardsRepository
import kotlinx.coroutines.launch

class AddViewModel() : ViewModel() {


    private val cardRepository = CardsRepository

    var name by mutableStateOf("")
    var number by mutableStateOf("")
    var balance by mutableStateOf("")
    var cardType by mutableStateOf("Credit Card")

    var cardColor by mutableIntStateOf(0)
        private set


    var isError by mutableStateOf(false)
    var mgsError by mutableStateOf("")


    fun onNameChange(newName: String) {
        name = newName
    }

    fun onNumberChange(newNumber: String) {
        number = newNumber
    }

    fun onBalanceChange(newBalance: String) {
        balance = newBalance
    }

    fun onCardTypeChange(newType: String) {
        cardType = newType
    }

    fun onCardColorChange(newColor: Int) {
        cardColor = newColor
    }

    fun addCard(onSuccess: (Int) -> Unit) {
        if (validateFields()) {
            viewModelScope.launch {
                cardRepository.insertCards(
                    Cards(
                        0,
                        cardName = name,
                        cardNumber = number,
                        balance = balance,
                        cardType = cardType,
                        color = cardColor
                    )
                )
                onSuccess(cardRepository.getCardsCount())
            }
        }
    }


    fun loadCardData(cardId: Int) {
        viewModelScope.launch {
            val card = cardRepository.getCardsById(cardId)
            card.let {
                name = it.cardName
                number = it.cardNumber
                balance = it.balance
                cardType = it.cardType
                cardColor = it.color
            }
        }
    }

    fun updateCard(cardId: Int, onSuccess: () -> Unit) {
        if (validateFields()) {
            viewModelScope.launch {
                cardRepository.updateCards(
                    Cards(
                        id = cardId,
                        cardName = name,
                        cardNumber = number,
                        balance = balance,
                        cardType = cardType,
                        color = cardColor
                    )
                )
                onSuccess()
            }
        }
    }

    fun clearFields() {
        name = ""
        number = ""
        balance = ""
        cardType = "Credit Card"
        cardColor = 0
    }

    fun validateFields(): Boolean {
        return if (name.isEmpty() || number.isEmpty() || balance.isEmpty()) {
            isError = true
            mgsError = when {
                name.isEmpty() -> "Please Insert The Name Person"
                number.isEmpty() -> "Please Insert Card Number"
                balance.isEmpty() -> "Please Insert The Balance"
                else -> "Unknown Error"
            }
            false
        } else {
            isError = false
            true
        }
    }


}