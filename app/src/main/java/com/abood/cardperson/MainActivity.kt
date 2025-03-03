package com.abood.cardperson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abood.cardperson.Presentation.addCard.AddCardScreen
import com.abood.cardperson.Presentation.listCard.HomeScreen
import com.abood.cardperson.ui.theme.CardPersonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardPersonTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.CardScreen.route) {
                    composable(Screen.CardScreen.route) {
                        HomeScreen(navController = navController)
                    }
                    composable(Screen.AddCardScreen.route) {
                        AddCardScreen(navController = navController)
                    }
                    composable(Screen.EditCardScreen.route) { backStackEntry ->
                        val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()
                        AddCardScreen(navController = navController, cardId = cardId)
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardPersonTheme {
        Greeting("Android")
    }
}