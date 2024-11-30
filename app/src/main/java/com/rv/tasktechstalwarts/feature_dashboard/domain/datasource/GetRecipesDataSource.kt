package com.rv.tasktechstalwarts.feature_dashboard.domain.datasource

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesResponse

interface GetRecipesDataSource {
    suspend fun getRecipesData(): GetRecipesResponse
}