package com.rv.tasktechstalwarts.feature_local_storage.data.datasource

import com.rv.tasktechstalwarts.common.localdatabase.MyLocalDao
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.feature_local_storage.domain.datasource.LocalStorageDataSource
import javax.inject.Inject

class LocalStorageDataSourceImpl @Inject constructor(private val localDao: MyLocalDao): LocalStorageDataSource {
    override suspend fun insertAddToCart(recipe: Recipe): Long {
        return localDao.insertAddToCartResponse(recipe)
    }

    override suspend fun getAddToCartList(): List<Recipe> {
     return localDao.getAddToCartList()
    }

    override suspend fun insertFavouriteData(favouriteListResponse: FavouriteListResponse): Long {
        return localDao.insertFavouriteData(favouriteListResponse)
    }

    override suspend fun getFavouriteList(): List<FavouriteListResponse> {
        return localDao.getFavouriteList()
    }
}