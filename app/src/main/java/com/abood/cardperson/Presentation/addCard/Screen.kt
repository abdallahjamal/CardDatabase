package com.abood.cardperson.Presentation.addCard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.abood.cardperson.Presentation.addCard.components.showDialog
import com.abood.cardperson.Presentation.getGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(
    navController: NavController, cardId: Int? = null
) {

    val viewModel: AddViewModel = viewModel()

    val context = LocalContext.current

    LaunchedEffect(cardId) {
        if (cardId != null) {
            viewModel.loadCardData(cardId)
        } else {
            viewModel.clearFields()
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (cardId == null) "Add New Card" else "Edit Card") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF00BFFF)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)

        ) {

            if (viewModel.isError) {
                showDialog(mgsError = viewModel.mgsError, onDismiss = { viewModel.isError = false })
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent) // استخدام تدرج الألوان
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(color = Color(viewModel.cardColor))
                        .padding(16.dp)
                ) {

                    TextField(
                        value = viewModel.name,
                        onValueChange = viewModel::onNameChange,
                        label = { Text("Name Person") },
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = viewModel.number,
                        onValueChange = viewModel::onNumberChange,
                        label = { Text("Card Number") },
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = viewModel.balance,
                        onValueChange = viewModel::onBalanceChange,
                        label = { Text("Balance") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Text(
                text = "Card Type",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = viewModel.cardType == "Credit Card",
                    onClick = { viewModel.onCardTypeChange("Credit Card") }
                )
                Text("Credit Card")
                RadioButton(
                    selected = viewModel.cardType == "Debit Card",
                    onClick = { viewModel.onCardTypeChange("Debit Card") }
                )
                Text("Debit card")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Choose card color",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ColorPickerOption(
                    gradient = getGradient(Color(0xFF00BFFF), Color(0xFF4682B4)),
                    onClick = { viewModel.onCardColorChange(0xFF00BFFF.toInt()) }
                )
                ColorPickerOption(
                    gradient = getGradient(Color(0xFF8A2BE2), Color(0xFF9370DB)),
                    onClick = { viewModel.onCardColorChange(0xFF8A2BE2.toInt()) }
                )
                ColorPickerOption(
                    gradient = getGradient(Color(0xFF1E90FF), Color(0xFF4169E1)),
                    onClick = { viewModel.onCardColorChange(0xFF1E90FF.toInt()) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (viewModel.validateFields()) {
                        if (cardId == null) {
                            viewModel.addCard {
                                navController.popBackStack()
                            }
                        } else {
                            viewModel.updateCard(cardId) {
                                navController.popBackStack()
                                Toast.makeText(
                                    context,
                                    "Card Updated!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (cardId == null) "Add Card" else "Update Card")
            }

        }

    }
}

@Composable
fun ColorPickerOption(gradient: Brush, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(gradient)
            .clickable { onClick() }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewCardDetailsScreen() {
//    CardDetailsScreen()
}
