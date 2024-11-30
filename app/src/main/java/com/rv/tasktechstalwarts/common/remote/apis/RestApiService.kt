package com.rv.tasktechstalwarts.common.remote.apis

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesResponse
import retrofit2.http.GET

/**
 *  Rest API
 */
interface RestApiService {

    @GET(GET_RECIPES)
    suspend fun getRecipes(): GetRecipesResponse

}
