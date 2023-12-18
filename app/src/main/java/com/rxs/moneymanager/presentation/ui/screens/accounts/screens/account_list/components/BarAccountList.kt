package com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rxs.moneymanager.R
import com.rxs.moneymanager.presentation.ui.navigation.Screen
import com.rxs.moneymanager.presentation.ui.theme.Orange

@Composable
fun BarAccountList(
    navController: NavHostController,
) {
    Row(
        modifier = Modifier
            .background(Orange)
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Счета",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(end = 24.dp)
                .size(24.dp)
                .clickable {
                    navController.navigate(Screen.AccountCreationScreen.route)
                }
        )
    }
}
