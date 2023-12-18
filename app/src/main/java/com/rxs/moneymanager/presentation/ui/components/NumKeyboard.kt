package com.rxs.moneymanager.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxs.moneymanager.R
import com.rxs.moneymanager.presentation.ui.theme.Gray3

@Composable
fun NumKeyboard(
    spaced: Dp = 12.dp,
    onNumKeyClick: (String) -> Unit = {},
    onClearClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spaced)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spaced, Alignment.CenterHorizontally)
        ) {
            NumKey(text = "1", onNumKeyClick = onNumKeyClick)
            NumKey(text = "2", onNumKeyClick = onNumKeyClick)
            NumKey(text = "3", onNumKeyClick = onNumKeyClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spaced, Alignment.CenterHorizontally)
        ) {
            NumKey(text = "4", onNumKeyClick = onNumKeyClick)
            NumKey(text = "5", onNumKeyClick = onNumKeyClick)
            NumKey(text = "6", onNumKeyClick = onNumKeyClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spaced, Alignment.CenterHorizontally)
        ) {
            NumKey(text = "7", onNumKeyClick = onNumKeyClick)
            NumKey(text = "8", onNumKeyClick = onNumKeyClick)
            NumKey(text = "9", onNumKeyClick = onNumKeyClick)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spaced, Alignment.CenterHorizontally)
        ) {
            NumKey()
            NumKey(text = "0", onNumKeyClick = onNumKeyClick)
            NumKey(icon = R.drawable.ic_delete, onClearClick = onClearClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NumKey(
    text: String? = null,
    icon: Int? = null,
    onNumKeyClick: (String) -> Unit = {},
    onClearClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .height(50.dp)
            .width(70.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(width = 1.dp, color = Gray3),
        onClick = {
            if (text != null) {
                onNumKeyClick(text)
            } else {
                onClearClick()
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (text != null) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                )
            } else if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}