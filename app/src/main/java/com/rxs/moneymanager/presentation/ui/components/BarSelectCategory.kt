package com.rxs.moneymanager.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxs.moneymanager.R

@Composable
fun BarSelectCategory(
    text: String = "",
    size: Int,
    index: MutableState<Int>,
    onIndexChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onIndexChange((index.value + size - 1) % size)
                }
        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight(400),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .scale(scaleX = -1f, scaleY = 1f)
                .clickable {
                    onIndexChange((index.value + 1) % size)
                }
        )
    }
}