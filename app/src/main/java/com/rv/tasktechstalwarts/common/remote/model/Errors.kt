package com.rv.tasktechstalwarts.common.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rv.tasktechstalwarts.common.EMPTY_STRING

import kotlinx.parcelize.Parcelize

@Parcelize
data class Errors(
    @SerializedName("type")
    val type: String? = EMPTY_STRING,
    @SerializedName("message")
    val message: String? = EMPTY_STRING
) : Parcelable