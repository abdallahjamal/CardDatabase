package com.abood.cardperson.domain

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.abood.cardperson.data.data_source.CardsDB

object Util {

    lateinit var cardsDb: CardsDB

    fun buildNotesDatabase(context: Context) {

        Log.d("TAG", "Database Initialization")

        cardsDb = Room.databaseBuilder(
            context,
            CardsDB::class.java,
            "card_db"
        ).build()
    }
}