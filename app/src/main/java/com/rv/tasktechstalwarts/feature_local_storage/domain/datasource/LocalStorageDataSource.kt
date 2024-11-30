package com.rv.tasktechstalwarts.feature_local_storage.domain.datasource

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe

interface LocalStorageDataSource {
    suspend fun insertAddToCart(recipe: Recipe): Long
    suspend fun getAddToCartList(): List<Recipe>
    suspend fun insertFavouriteData(favouriteListResponse: FavouriteListResponse): Long
    suspend fun getFavouriteList(): List<FavouriteListResponse>
}