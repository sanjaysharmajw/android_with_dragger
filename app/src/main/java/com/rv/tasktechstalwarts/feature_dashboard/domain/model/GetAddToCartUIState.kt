package com.rv.tasktechstalwarts.feature_dashboard.domain.model

sealed class GetAddToCartUIState {
    data class IsLoading(val isLoading: Boolean) : GetAddToCartUIState()
    data class OnSuccess(val onSuccess: List<Recipe>) : GetAddToCartUIState()
    data class OnFailure(val onFailure: String) : GetAddToCartUIState()
}