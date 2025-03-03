package com.abood.cardperson.Presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abood.cardperson.R
import com.abood.cardperson.domain.model.Cards

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

@Preview
@Composable
fun CardsSection() {
    LazyColumn {
//        items(cards.size) { index ->
////            CardItem(index){}
//        }
    }
}

@Composable
fun CardItem(
    index: Int, onClick: () -> Unit, onEditClick: () -> Unit,
    cards: List<Cards>,
) {

    val card = cards[index]
    var lastItemPaddingEnd = 0.dp
    if (index == cards.size - 1) {
        lastItemPaddingEnd = 16.dp
    }

    var image = painterResource(id = R.drawable.ic_visa)
    if (card.cardType == "Debit Card") {
        image = painterResource(id = R.drawable.ic_mastercard)
    }

    Box(
        modifier = Modifier
            .padding(top = 16.dp, bottom = lastItemPaddingEnd)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color(card.color)) // تغيير لون الخلفية هنا
                .fillMaxWidth()
                .height(160.dp)
                .clickable { onEditClick() }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image,
                    contentDescription = card.cardName,
                    modifier = Modifier.width(60.dp)
                )

                IconButton(onClick = {
                    onClick()
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
            txt(card = card.cardName, fontSize = 17.sp)
            txt(card = "$ ${card.balance}", fontSize = 22.sp)
            txt(card = card.cardNumber, fontSize = 18.sp)

        }

    }
}

@Composable
fun txt(card: String, fontSize: TextUnit) {
    Text(
        text = card,
        color = Color.White,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}

