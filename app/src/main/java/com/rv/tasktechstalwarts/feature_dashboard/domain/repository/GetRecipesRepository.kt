package com.rv.tasktechstalwarts.feature_dashboard.domain.repository

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesResponse

interface GetRecipesRepository {
    suspend fun getRecipesData(): GetRecipesResponse
}