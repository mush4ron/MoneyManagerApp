package com.rxs.moneymanager.domain.use_case

import com.rxs.moneymanager.data.model.Account
import com.rxs.moneymanager.domain.repository.DataRepository
import com.rxs.moneymanager.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateAccountUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun invoke(oldAccountId: String, account: Account) = withContext(dispatcherProvider.io) {
        dataRepository.updateAccount(oldAccountId, account)
    }
}