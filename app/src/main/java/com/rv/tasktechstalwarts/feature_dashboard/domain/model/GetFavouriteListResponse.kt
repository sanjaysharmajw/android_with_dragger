package com.rv.tasktechstalwarts.feature_dashboard.domain.model

sealed class GetFavouriteListUIState {
    data class IsLoading(val isLoading: Boolean) : GetFavouriteListUIState()
    data class OnSuccess(val onSuccess: List<FavouriteListResponse>) : GetFavouriteListUIState()
    data class OnFailure(val onFailure: String) : GetFavouriteListUIState()
}