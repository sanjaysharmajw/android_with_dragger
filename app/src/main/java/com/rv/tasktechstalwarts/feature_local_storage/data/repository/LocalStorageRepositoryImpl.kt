package com.rv.tasktechstalwarts.feature_local_storage.data.repository

import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.feature_local_storage.domain.datasource.LocalStorageDataSource
import com.rv.tasktechstalwarts.feature_local_storage.domain.repository.LocalStorageRepository
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(private val localStorageDataSource: LocalStorageDataSource) :
    LocalStorageRepository {
    override suspend fun insertAddToCart(recipe: Recipe): Long {
        return localStorageDataSource.insertAddToCart(recipe)
    }

    override suspend fun getAddToCartList(): List<Recipe> {
        return localStorageDataSource.getAddToCartList()
    }

    override suspend fun insertFavouriteData(favouriteListResponse: FavouriteListResponse): Long {
       return localStorageDataSource.insertFavouriteData(favouriteListResponse)
    }

    override suspend fun getFavouriteList(): List<FavouriteListResponse> {
        return localStorageDataSource.getFavouriteList()
    }
}