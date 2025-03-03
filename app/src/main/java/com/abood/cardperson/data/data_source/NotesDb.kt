package com.abood.cardperson.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abood.cardperson.data.data_source.dao.CardsDao
import com.abood.cardperson.domain.model.Cards


@Database(
    entities = [
        Cards::class
    ],
    version = 1
)
abstract class CardsDB : RoomDatabase() {
    abstract val cardsDao: CardsDao
}