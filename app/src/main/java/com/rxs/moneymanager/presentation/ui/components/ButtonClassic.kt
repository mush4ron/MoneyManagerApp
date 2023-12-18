package com.rxs.moneymanager.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxs.moneymanager.presentation.ui.theme.Orange

@Composable
fun ButtonClassic(
    modifier: Modifier = Modifier,
    color: Color = Orange,
    text: String = "Нажми меня",
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(horizontal = 24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight(400)
        )
    }
}