package com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rxs.moneymanager.presentation.ui.navigation.Screen
import com.rxs.moneymanager.presentation.ui.screens.accounts.AccountsViewModel
import com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_list.components.ItemAccountList
import com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_list.components.BarAccountList


@Composable
fun AccountListScreen(
    navController: NavHostController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BarAccountList(navController)
        LazyColumn {
            items(state.accounts) { account ->
                ItemAccountList(
                    name = account.accountName!!,
                    value = account.accountValue,
                    onClickDelete = {
                        viewModel.deleteAccount(accountId = account.accountId!!)
                    },
                    onClickEdit = {
                        navController.navigate(
                            Screen.AccountEditScreen.passId(
                                state.accounts.indexOf(
                                    account
                                )
                            )
                        )
                    }
                )
            }
        }
    }
}