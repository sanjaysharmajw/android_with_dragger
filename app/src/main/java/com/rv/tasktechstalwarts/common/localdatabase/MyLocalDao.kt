package com.rv.tasktechstalwarts.common.localdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rv.tasktechstalwarts.common.FAVOURITE_ENTITY
import com.rv.tasktechstalwarts.common.RECIPES_ENTITY
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe

@Dao
interface MyLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddToCartResponse(recipe: Recipe): Long

    @Query("SELECT * FROM $RECIPES_ENTITY")
    suspend fun getAddToCartList(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteData(favouriteListResponse: FavouriteListResponse): Long

    @Query("SELECT * FROM $FAVOURITE_ENTITY")
    suspend fun getFavouriteList(): List<FavouriteListResponse>
}