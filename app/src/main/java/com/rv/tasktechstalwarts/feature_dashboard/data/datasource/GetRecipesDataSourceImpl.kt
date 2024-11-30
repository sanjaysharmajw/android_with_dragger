package com.rv.tasktechstalwarts.feature_dashboard.data.datasource

import com.rv.tasktechstalwarts.common.remote.apis.RestApiService
import com.rv.tasktechstalwarts.feature_dashboard.domain.datasource.GetRecipesDataSource
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesResponse
import javax.inject.Inject

class GetRecipesDataSourceImpl @Inject constructor(private val restApiService: RestApiService): GetRecipesDataSource{
    override suspend fun getRecipesData(): GetRecipesResponse {
        return restApiService.getRecipes()
    }
}