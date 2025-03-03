package com.abood.cardperson.data.repository

import androidx.lifecycle.LiveData
import com.abood.cardperson.data.data_source.dao.CardsDao
import com.abood.cardperson.domain.Util

import com.abood.cardperson.domain.model.Cards

object CardsRepository {

    private val cardsDao: CardsDao = Util.cardsDb.cardsDao

    suspend fun insertCards(cards: Cards) = cardsDao.insertCards(cards)

    suspend fun updateCards(cards: Cards) = cardsDao.updateCards(cards)

    suspend fun upsertCards(cards: Cards) = cardsDao.upsertCards(cards)

    suspend fun deleteCards(cards: Cards) = cardsDao.deleteCards(cards)

     fun getAllCards(): LiveData<List<Cards>> = cardsDao.getAllCards()

    suspend fun getCardsById(id: Int): Cards = cardsDao.getCardsById(id)

    suspend fun getCardsCount(): Int = cardsDao.getCardsCount()
}