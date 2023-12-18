package com.rxs.moneymanager.presentation.ui.screens.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rxs.moneymanager.presentation.ui.animation.LoadingAnimation
import com.rxs.moneymanager.presentation.ui.components.BarBaseTop
import com.rxs.moneymanager.presentation.ui.components.BarInputNum
import com.rxs.moneymanager.presentation.ui.components.BarSelectAccount
import com.rxs.moneymanager.presentation.ui.components.BarSelectCategory
import com.rxs.moneymanager.presentation.ui.components.ButtonClassic
import com.rxs.moneymanager.presentation.ui.components.NumKeyboard
import com.rxs.moneymanager.presentation.ui.theme.Gray4
import com.rxs.moneymanager.presentation.ui.theme.Red

@Composable
fun ExpenseScreenSave(
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if (state.accounts.isEmpty() || state.categories.isEmpty()) {
        LoadingAnimation(color = Red)
    } else {
        val accountIndex = remember { mutableStateOf(0) }
        val categoryIndex = remember { mutableStateOf(0) }
        val balance = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray4)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BarBaseTop(color = Red, text = "Расходы")
                Spacer(modifier = Modifier.height(16.dp))
                BarSelectAccount(
                    text = state.accounts[accountIndex.value].accountName!!,
                    value = state.accounts[accountIndex.value].accountValue,
                    size = state.accounts.size,
                    index = accountIndex,
                    onIndexChange = { newIndex ->
                        accountIndex.value = newIndex
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                BarInputNum(text = balance.value)
                Spacer(modifier = Modifier.height(16.dp))
                NumKeyboard(
                    onNumKeyClick = { value ->
                        balance.value += value
                    },
                    onClearClick = {
                        if (balance.value.isNotEmpty()) {
                            balance.value = balance.value.dropLast(1)
                        }
                    })
                Spacer(modifier = Modifier.height(16.dp))
                BarSelectCategory(
                    text = state.categories[categoryIndex.value].categoryName ?: "",
                    size = state.categories.size,
                    index = categoryIndex,
                    onIndexChange = { newIndex ->
                        categoryIndex.value = newIndex
                    }
                )
            }
            ButtonClassic(
                color = Red,
                text = "Добавить",
                onClick = {
                    if (balance.value.isNotEmpty()) {
                        viewModel.makeTransaction(
                            transactionValue = balance.value.toInt(),
                            accountIndex = accountIndex.value,
                            categoryIndex = categoryIndex.value
                        )
                        balance.value = ""
                    }
                }
            )
        }
    }
}