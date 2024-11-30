package com.rv.tasktechstalwarts.feature_local_storage.domain.repository

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe

interface LocalStorageRepository {
    suspend fun insertAddToCart(recipe: Recipe): Long
    suspend fun getAddToCartList(): List<Recipe>
    suspend fun insertFavouriteData(favouriteListResponse: FavouriteListResponse): Long
    suspend fun getFavouriteList(): List<FavouriteListResponse>
}