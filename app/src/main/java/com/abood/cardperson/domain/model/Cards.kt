package com.abood.cardperson.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cards")
data class Cards(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cardName: String,
    val cardNumber: String,
    val balance: String,
    val cardType: String,
    val color: Int
)
