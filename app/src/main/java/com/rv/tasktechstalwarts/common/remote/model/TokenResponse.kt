package com.rv.tasktechstalwarts.common.remote.model

import android.os.Parcelable
import com.rv.tasktechstalwarts.common.remote.apis.TOKEN_TYPE
import com.mtp.scanner.common.storage.PrefConstants.ACCESS_TOKEN
import com.google.gson.annotations.SerializedName
import com.rv.tasktechstalwarts.common.remote.apis.REFRESH_TOKEN
import kotlinx.parcelize.Parcelize

/**
 * Token Response will save response data received from API
 * */
@Parcelize
data class TokenResponse(
    @SerializedName(ACCESS_TOKEN)
    var accessToken: String?,
    @SerializedName(TOKEN_TYPE)
    var tokenType: String?,
    @SerializedName(REFRESH_TOKEN)
    var refreshToken: String? = null,
) : Parcelable