package com.rxs.moneymanager.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rxs.moneymanager.data.model.Account
import com.rxs.moneymanager.data.model.Category
import com.rxs.moneymanager.data.model.Transaction
import com.rxs.moneymanager.domain.repository.DataRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Firebase : DataRepository {

    override suspend fun getAccounts(): List<Account> = coroutineScope {
        val dbRef = FirebaseDatabase.getInstance().getReference("accounts")
        val accounts = mutableListOf<Account>()

        suspendCoroutine { continuation ->
            dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (accSnap in snapshot.children) {
                            val accData = accSnap.getValue(Account::class.java)
                            accounts.add(accData!!)
                        }
                        continuation.resume(accounts)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(emptyList())
                }
            })
        }
    }

    override suspend fun saveAccount(account: Account) {
        val dbRef = FirebaseDatabase.getInstance().getReference("accounts")
        val categoryId = dbRef.push().key!!
        dbRef.child(categoryId).setValue(account.copy(accountId = categoryId))
    }

    override suspend fun updateAccount(oldAccountId: String, account: Account) {
        val dbRef = FirebaseDatabase.getInstance().getReference("accounts").child(oldAccountId)
        dbRef.setValue(account.copy(accountId = oldAccountId))
    }

    override suspend fun deleteAccount(accountId: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("accounts").child(accountId)
        dbRef.removeValue().await()

        for (transaction in getTransactions()) {
            if (transaction.accountId == accountId) {
                val transRef = FirebaseDatabase.getInstance().getReference("transactions")
                    .child(transaction.transactionId!!)
                transRef.removeValue().await()
            }
        }
    }

    override suspend fun getCategories(): List<Category> = coroutineScope {
        val dbRef = FirebaseDatabase.getInstance().getReference("categories")
        val categories = mutableListOf<Category>()

        suspendCoroutine { continuation ->
            dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (catSnap in snapshot.children) {
                            val catData = catSnap.getValue(Category::class.java)
                            categories.add(catData!!)
                        }
                        continuation.resume(categories)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(emptyList())
                }
            })
        }
    }

    override suspend fun saveCategory(category: Category) {
        val dbRef = FirebaseDatabase.getInstance().getReference("categories")
        val categoryId = dbRef.push().key!!
        dbRef.child(categoryId).setValue(category.copy(categoryId = categoryId))
    }

    override suspend fun getTransactions(): List<Transaction> = coroutineScope {
        val dbRef = FirebaseDatabase.getInstance().getReference("transactions")
        val categories = mutableListOf<Transaction>()

        suspendCoroutine { continuation ->
            dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (transSnap in snapshot.children) {
                            val transData = transSnap.getValue(Transaction::class.java)
                            categories.add(transData!!)
                        }
                        continuation.resume(categories)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(emptyList())
                }
            })
        }
    }

    override suspend fun makeTransaction(transaction: Transaction) {
        val dbRef = FirebaseDatabase.getInstance().getReference("transactions")
        val transactionId = dbRef.push().key!!
        dbRef.child(transactionId).setValue(transaction.copy(transactionId = transactionId))

        val accountRef =
            FirebaseDatabase.getInstance().getReference("accounts").child(transaction.accountId!!)
        accountRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val oldValue = snapshot.child("accountValue").getValue(Int::class.java)!!
                accountRef.child("accountValue").setValue(oldValue - transaction.transactionValue)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}