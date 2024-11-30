package com.rv.tasktechstalwarts.common.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rv.tasktechstalwarts.common.VERSION_NUMBER
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe

@Database(entities = [Recipe::class, FavouriteListResponse::class], version = VERSION_NUMBER)
abstract class MyLocalDatabase: RoomDatabase() {
    abstract fun tpScanDao(): MyLocalDao
}