package com.rv.tasktechstalwarts.feature_dashboard.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rv.tasktechstalwarts.common.FAVOURITE_ENTITY
import kotlinx.parcelize.Parcelize

@Entity(tableName = FAVOURITE_ENTITY)
@Parcelize
data class  FavouriteListResponse(
    @ColumnInfo("caloriesPerServing")
    val caloriesPerServing: Int?,
    @ColumnInfo("cookTimeMinutes")
    val cookTimeMinutes: Int?,
    @ColumnInfo("cuisine")
    val cuisine: String?,
    @ColumnInfo("difficulty")
    val difficulty: String?,
    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("prepTimeMinutes")
    val prepTimeMinutes: Int?,
    @ColumnInfo("rating")
    val rating: Double?,
    @ColumnInfo("reviewCount")
    val reviewCount: Int?,
    @ColumnInfo("servings")
    val servings: Int?,
    @PrimaryKey
    val userId: Int?
) : Parcelable
