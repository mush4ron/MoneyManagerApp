package com.rxs.moneymanager.presentation.ui.screens.income

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxs.moneymanager.R
import com.rxs.moneymanager.presentation.ui.theme.Border
import com.rxs.moneymanager.presentation.ui.theme.Gray
import com.rxs.moneymanager.presentation.ui.theme.SecondBackground

@Preview(showBackground = true)
@Composable
fun IncomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IncomeKeyboard()
    }
}

@Preview
@Composable
fun IncomeKeyboard(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IncomeKey(modifier = Modifier.weight(1f), text = "1")
            IncomeKey(modifier = Modifier.weight(1f), text = "2")
            IncomeKey(modifier = Modifier.weight(1f), text = "3")
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            IncomeKey(modifier = Modifier.weight(1f), text = "4")
            IncomeKey(modifier = Modifier.weight(1f), text = "5")
            IncomeKey(modifier = Modifier.weight(1f), text = "6")
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            IncomeKey(modifier = Modifier.weight(1f), text = "7")
            IncomeKey(modifier = Modifier.weight(1f), text = "8")
            IncomeKey(modifier = Modifier.weight(1f), text = "9")
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            IncomeKey(modifier = Modifier.weight(1f), text = "")
            IncomeKey(modifier = Modifier.weight(1f), text = "0")
            IncomeKey(
                modifier = Modifier.weight(1f),
                iconId = R.drawable.ic_erase,
                iconSize = 20.dp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun IncomeKey(
    modifier: Modifier = Modifier,
    text: String? = null,
    iconId: Int? = null,
    iconSize: Dp = 16.dp
) {
    Card(
        modifier = modifier
            .height(35.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = SecondBackground),
        border = BorderStroke(width = 1.dp, color = Border),
        onClick = {
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
                    fontSize = 16.sp,
                    color = Gray,
                )
            } else if (iconId != null) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    tint = Gray,
                    modifier = Modifier
                        .padding(top = 1.dp)
                        .size(iconSize)
                )
            }
        }
    }
}

