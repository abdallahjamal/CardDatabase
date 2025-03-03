package com.abood.cardperson


sealed class Screen(val route : String) {
    object CardScreen: Screen("card_screen")
    object AddCardScreen: Screen("add_card_screen")

    object EditCardScreen: Screen("edit_card_screen/{cardId}") {
        fun createRoute(cardId: String) = "edit_card_screen/$cardId"
    }
}