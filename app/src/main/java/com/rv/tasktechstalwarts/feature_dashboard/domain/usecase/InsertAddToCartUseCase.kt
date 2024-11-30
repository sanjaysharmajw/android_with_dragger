package com.rv.tasktechstalwarts.feature_dashboard.domain.usecase

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.feature_local_storage.domain.repository.LocalStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class InsertAddToCartUseCase @Inject constructor(private val localStorageRepository: LocalStorageRepository) {
   suspend fun invoke(recipe: Recipe): Flow<Long> {
        return flow {
            emit(localStorageRepository.insertAddToCart(recipe))
        }.flowOn(Dispatchers.IO)
    }
}