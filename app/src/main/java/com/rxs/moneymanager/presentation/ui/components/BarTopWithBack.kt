package com.rxs.moneymanager.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxs.moneymanager.R
import com.rxs.moneymanager.presentation.ui.theme.Orange

@Composable
fun BarTopWithBack(
    color: Color = Orange,
    text: String = "Счета",
    onBackClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(vertical = 10.dp, horizontal = 24.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onBackClicked()
                }
        )
        Text(
            text = text.uppercase(),
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp),
            textAlign = TextAlign.Center
        )
    }
}