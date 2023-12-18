package com.rxs.moneymanager.presentation.ui.screens.expense

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.moneymanager.data.model.Transaction
import com.rxs.moneymanager.data.model.TransactionType
import com.rxs.moneymanager.domain.use_case.GetAccountsUseCase
import com.rxs.moneymanager.domain.use_case.GetCategoriesUseCase
import com.rxs.moneymanager.domain.use_case.MakeTransactionUseCase
import com.rxs.moneymanager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val makeTransactionUseCase: MakeTransactionUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ExpenseState())
    val state: State<ExpenseState> = _state

    init {
        getAccounts()
        getCategories()
    }

    fun makeTransaction(transactionValue: Int, accountIndex: Int, categoryIndex: Int) {
        changeAccountValue(transactionValue, accountIndex)
        viewModelScope.launch {
            makeTransactionUseCase.invoke(
                transaction = Transaction(
                    accountId = _state.value.accounts[accountIndex].accountId,
                    categoryId = _state.value.categories[categoryIndex].categoryId,
                    transactionValue = transactionValue,
                    transactionType = TransactionType.EXPENSE.toString()
                )
            )
        }
    }

    private fun changeAccountValue(transactionValue: Int, accountIndex: Int) {
        val newState = _state.value.copy()
        newState.accounts[accountIndex].accountValue -= transactionValue
        _state.value = newState
    }

    private fun getAccounts() {
        getAccountsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(accounts = result.data ?: emptyList())
                }

                is Resource.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(categories = result.data ?: emptyList())
                }

                is Resource.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}