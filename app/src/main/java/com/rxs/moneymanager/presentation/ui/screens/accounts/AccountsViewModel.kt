package com.rxs.moneymanager.presentation.ui.screens.accounts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.moneymanager.data.model.Account
import com.rxs.moneymanager.domain.use_case.DeleteAccountUseCase
import com.rxs.moneymanager.domain.use_case.GetAccountsUseCase
import com.rxs.moneymanager.domain.use_case.SaveAccountUseCase
import com.rxs.moneymanager.domain.use_case.UpdateAccountUseCase
import com.rxs.moneymanager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val saveAccountUseCase: SaveAccountUseCase,
    private val getAccountsUseCase: GetAccountsUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AccountsState())
    val state: State<AccountsState> = _state

    init {
        getAccounts()
    }

    fun saveAccount(accountName: String, accountValue: Int) {
        viewModelScope.launch {
            saveAccountUseCase.invoke(
                Account(
                    accountName = accountName,
                    accountValue = accountValue
                )
            )
        }
    }

    fun updateAccount(oldAccountId: String, accountName: String, accountValue: Int) {
        viewModelScope.launch {
            updateAccountUseCase.invoke(
                oldAccountId = oldAccountId,
                account = Account(accountName = accountName, accountValue = accountValue)
            )
        }
    }

    fun deleteAccount(accountId: String) {
        _state.value = _state.value.copy(accounts = _state.value.accounts.filter {
            it.accountId != accountId
        })
        viewModelScope.launch {
            deleteAccountUseCase.invoke(accountId = accountId)
        }
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
}