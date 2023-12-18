package com.rxs.moneymanager.domain.repository

import com.rxs.moneymanager.data.model.Account
import com.rxs.moneymanager.data.model.Category
import com.rxs.moneymanager.data.model.Transaction

interface DataRepository {

    suspend fun getAccounts(): List<Account>
    suspend fun saveAccount(account: Account)
    suspend fun updateAccount(oldAccountId: String, account: Account)
    suspend fun deleteAccount(accountId: String)

    suspend fun getCategories(): List<Category>
    suspend fun saveCategory(category: Category)

    suspend fun getTransactions(): List<Transaction>
    suspend fun makeTransaction(transaction: Transaction)
}