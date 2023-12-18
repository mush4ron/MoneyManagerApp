package com.rxs.moneymanager.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxs.moneymanager.presentation.ui.theme.Orange

@Preview(showBackground = true)
@Composable
fun BarBaseTop(
    color: Color = Orange,
    text: String = "Счета"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(vertical = 10.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            color = Color.White
        )
    }
}