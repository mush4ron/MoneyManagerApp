package com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rxs.moneymanager.presentation.ui.screens.accounts.AccountsViewModel
import com.rxs.moneymanager.presentation.ui.theme.Gray2
import com.rxs.moneymanager.presentation.ui.theme.Gray3
import com.rxs.moneymanager.presentation.ui.theme.Gray4
import com.rxs.moneymanager.presentation.ui.theme.Orange
import com.rxs.moneymanager.presentation.ui.components.ButtonClassic
import com.rxs.moneymanager.presentation.ui.components.BarTopWithBack
import com.rxs.moneymanager.presentation.ui.navigation.Screen
import com.rxs.moneymanager.presentation.ui.theme.Gray1

@Composable
fun AccountEditScreen(
    navController: NavHostController,
    accountIndex: Int,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if (state.accounts.isNotEmpty()) {
        var name by remember { mutableStateOf(state.accounts[accountIndex].accountName!!) }
        var value by remember { mutableStateOf(state.accounts[accountIndex].accountValue.toString()) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray4),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                BarTopWithBack(
                    color = Orange,
                    text = "Изменить счет"
                ) {
                    navController.navigate(Screen.AccountListScreen.route)
                }
                Column(Modifier.padding(top = 40.dp)) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(text = "Название") },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Gray3,
                            focusedContainerColor = Gray3,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Gray2,
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    )
                    TextField(
                        value = value,
                        onValueChange = { value = it },
                        label = { Text(text = "Баланс") },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Gray3,
                            focusedContainerColor = Gray3,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Gray1,
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    )
                }
            }
            ButtonClassic(
                modifier = Modifier.padding(bottom = 24.dp),
                color = Orange,
                text = "Изменить",
                onClick = {
                    viewModel.updateAccount(oldAccountId = state.accounts[accountIndex].accountId!!, accountName = name, accountValue = value.toInt())
                    navController.navigate(Screen.AccountListScreen.route)
                }
            )
        }
    }
}