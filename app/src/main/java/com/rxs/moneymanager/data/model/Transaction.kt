package com.rxs.moneymanager.data.model

data class Transaction(
    val transactionId: String? = null,
    val accountId: String? = null,
    val categoryId: String? = null,
    val transactionValue: Int = 0,
    val transactionType: String? = null,
)