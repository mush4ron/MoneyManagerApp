package com.rxs.moneymanager.presentation.ui.navigation

import com.rxs.moneymanager.R

sealed class Screen(val label: String?, val icon: Int?, val route: String) {
    object IncomeScreen :
        Screen(label = "Доход", icon = R.drawable.ic_income, route = "income_main")

    object ExpenseScreen :
        Screen(label = "Расход", icon = R.drawable.ic_expense, route = "expense_main")

    object AccountListScreen :
        Screen(label = "Счета", icon = R.drawable.ic_accounts, route = "accounts_list")

    object AccountCreationScreen : Screen(label = null, null, "accounts_create")
    object AccountEditScreen : Screen(label = null, null, "accounts_edit/{id}") {
        fun passId(id: Int): String {
            return "accounts_edit/$id"
        }
    }
}
