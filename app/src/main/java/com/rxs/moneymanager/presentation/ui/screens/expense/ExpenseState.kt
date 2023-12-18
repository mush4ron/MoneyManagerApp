package com.rxs.moneymanager.presentation.ui.screens.expense

import com.rxs.moneymanager.data.model.Account
import com.rxs.moneymanager.data.model.Category

data class ExpenseState(
    val accounts: List<Account> = emptyList(),
    val categories: List<Category> = emptyList(),
    val error: String = ""
)