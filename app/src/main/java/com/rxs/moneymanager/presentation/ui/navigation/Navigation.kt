package com.rxs.moneymanager.presentation.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_create.AccountCreationScreen
import com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_edit.AccountEditScreen
import com.rxs.moneymanager.presentation.ui.screens.accounts.screens.account_list.AccountListScreen
import com.rxs.moneymanager.presentation.ui.screens.expense.ExpenseScreenSave
import com.rxs.moneymanager.presentation.ui.screens.income.IncomeScreen
import com.rxs.moneymanager.presentation.ui.theme.Gray2
import com.rxs.moneymanager.presentation.ui.theme.Gray5
import com.rxs.moneymanager.presentation.ui.theme.Orange
import com.rxs.moneymanager.presentation.ui.theme.Purple
import com.rxs.moneymanager.presentation.ui.theme.Red

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.IncomeScreen.route
    ) {
        composable(route = Screen.IncomeScreen.route) {
            IncomeScreen()
        }
        composable(route = Screen.ExpenseScreen.route) {
            ExpenseScreenSave()
        }
        composable(route = Screen.AccountListScreen.route) {
            AccountListScreen(navController)
        }
        composable(route = Screen.AccountCreationScreen.route) {
            AccountCreationScreen(navController)
        }
        composable(
            route = "accounts_edit/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
                AccountEditScreen(
                    navController = navController,
                    accountIndex = it.arguments?.getInt("id")!!
                )
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    val screens = listOf(
        Screen.IncomeScreen,
        Screen.ExpenseScreen,
        Screen.AccountListScreen
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        containerColor = Gray5,
        contentColor = Gray2,
    ) {
        screens.forEach { screen ->
            val selected = screen.route == backStackEntry.value?.destination?.route
            val color = when (screen.route) {
                Screen.IncomeScreen.route -> Purple
                Screen.ExpenseScreen.route -> Red
                Screen.AccountListScreen.route -> Orange
                else -> Gray2
            }
            NavigationBarItem(
                label = {
                    Text(
                        text = screen.label!!,
                        color = if (selected) color else Gray2
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(screen.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Gray5
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon!!),
                        contentDescription = screen.route,
                        tint = if (selected) color else Gray2,
                        modifier = Modifier.size(20.dp),
                    )
                }
            )
        }
    }
}