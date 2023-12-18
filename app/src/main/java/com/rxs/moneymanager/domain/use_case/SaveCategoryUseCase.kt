package com.rxs.moneymanager.domain.use_case

import com.rxs.moneymanager.data.model.Category
import com.rxs.moneymanager.domain.repository.DataRepository
import com.rxs.moneymanager.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveCategoryUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun invoke(category: Category) = withContext(dispatcherProvider.io) {
        dataRepository.saveCategory(category)
    }
}