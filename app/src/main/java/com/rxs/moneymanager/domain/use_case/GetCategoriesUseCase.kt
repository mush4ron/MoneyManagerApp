package com.rxs.moneymanager.domain.use_case

import android.util.Log
import com.rxs.moneymanager.data.model.Account
import com.rxs.moneymanager.data.model.Category
import com.rxs.moneymanager.domain.repository.DataRepository
import com.rxs.moneymanager.utils.DispatcherProvider
import com.rxs.moneymanager.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            val categories = dataRepository.getCategories()
            emit(Resource.Success(categories))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }.flowOn(dispatcherProvider.io)
}