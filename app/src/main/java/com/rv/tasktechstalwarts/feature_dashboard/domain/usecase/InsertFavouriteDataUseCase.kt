package com.rv.tasktechstalwarts.feature_dashboard.domain.usecase

import android.print.PrinterId
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.feature_local_storage.domain.repository.LocalStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertFavouriteDataUseCase @Inject constructor(private val localStorageRepository: LocalStorageRepository) {
    suspend fun invoke(favouriteListResponse: FavouriteListResponse): Flow<Long>{
        return flow {
            emit(localStorageRepository.insertFavouriteData(favouriteListResponse))
        }.flowOn(Dispatchers.IO)
    }
}