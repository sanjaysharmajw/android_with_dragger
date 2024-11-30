package com.rv.tasktechstalwarts.feature_dashboard.domain.model

sealed class InsertFavouriteUIState {
    data class IsLoading(val isLoading: Boolean) : InsertFavouriteUIState()
    data class OnSuccess(val onSuccess: Long) : InsertFavouriteUIState()
    data class OnFailure(val onFailure: String) : InsertFavouriteUIState()
}