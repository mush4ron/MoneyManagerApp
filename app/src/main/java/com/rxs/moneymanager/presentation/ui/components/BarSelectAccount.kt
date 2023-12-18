package com.rxs.moneymanager.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxs.moneymanager.R
import com.rxs.moneymanager.presentation.ui.theme.Gray2
import com.rxs.moneymanager.presentation.ui.theme.Gray3

@Composable
fun BarSelectAccount(
    text: String = "",
    value: Int = 0,
    size: Int,
    index: MutableState<Int>,
    onIndexChange: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            tint = Gray2,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onIndexChange((index.value + size - 1) % size)
                }
        )
        Card(
            modifier = Modifier
                .width(180.dp)
                .height(50.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(width = 1.dp, color = Gray3)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black
                )
                Text(
                    text = value.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black
                )
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            tint = Gray2,
            modifier = Modifier
                .size(24.dp)
                .scale(scaleX = -1f, scaleY = 1f)
                .clickable {
                    onIndexChange((index.value + 1) % size)
                }
        )
    }
}