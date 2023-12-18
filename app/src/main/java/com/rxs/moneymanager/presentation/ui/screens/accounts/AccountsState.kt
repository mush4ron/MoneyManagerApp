package com.rxs.moneymanager.presentation.ui.screens.accounts

import com.rxs.moneymanager.data.model.Account

data class AccountsState(
    val accounts: List<Account> = emptyList(),
    val error: String = ""
)