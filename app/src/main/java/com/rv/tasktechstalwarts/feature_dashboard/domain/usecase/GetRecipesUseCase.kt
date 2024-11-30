package com.rv.tasktechstalwarts.feature_dashboard.domain.usecase

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.repository.GetRecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: GetRecipesRepository) {
    suspend fun invoke(): Flow<GetRecipesResponse> {
        return flow{
            emit(repository.getRecipesData())
        }.flowOn(Dispatchers.IO)
    }
}