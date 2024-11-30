package com.rv.tasktechstalwarts.feature_dashboard.data.repository

import com.rv.tasktechstalwarts.feature_dashboard.domain.datasource.GetRecipesDataSource
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.repository.GetRecipesRepository
import javax.inject.Inject

class GetRecipesRepositoryImpl @Inject constructor(private val getRecipesDataSource: GetRecipesDataSource): GetRecipesRepository{
    override suspend fun getRecipesData(): GetRecipesResponse {
        return getRecipesDataSource.getRecipesData()
    }
}