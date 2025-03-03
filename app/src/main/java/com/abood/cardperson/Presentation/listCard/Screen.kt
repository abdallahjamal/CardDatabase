package com.abood.cardperson.Presentation.listCard

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.abood.cardperson.Presentation.CardItem
import com.abood.cardperson.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ListViewModel = viewModel(),
) {


    val context = LocalContext.current
    val data by viewModel.allCards.observeAsState(emptyList()) // مراقبة LiveData

    Toast.makeText(context, viewModel.getCount().toString(), Toast.LENGTH_SHORT).show()

    val colorScheme = MaterialTheme.colorScheme
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Card List", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.primary,
                    titleContentColor = colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddCardScreen.route)
            }, containerColor = colorScheme.primary) {
                Text(
                    "+",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onPrimary
                )

            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (data.isEmpty()) {
                Text(
                    text = "No Note Found",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(data) { card ->
                        CardItem(
                            index = data.indexOf(card),
                            onClick = {
                                viewModel.deleteCards(card)
                            }, onEditClick = {
                                navController.navigate(Screen.EditCardScreen.createRoute(card.id.toString()))
                            },

                            cards = data
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CardsSection() {
//    HomeScreen()
}
