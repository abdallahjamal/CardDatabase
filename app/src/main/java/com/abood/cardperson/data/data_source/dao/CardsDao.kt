package com.abood.cardperson.data.data_source.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abood.cardperson.domain.model.Cards

@Dao
interface CardsDao {

    @Insert
    suspend fun insertCards(card: Cards)

    @Update
    suspend fun updateCards(card: Cards)

    @Upsert
    suspend fun upsertCards(card: Cards)

    @Delete
    suspend fun deleteCards(card: Cards)

    @Query("SELECT * FROM cards")
    fun getAllCards(): LiveData<List<Cards>>

    @Query("SELECT * FROM cards WHERE id = :id")
    suspend fun getCardsById(id: Int): Cards

    @Query("SELECT COUNT(*) FROM cards")
    suspend fun getCardsCount(): Int
}