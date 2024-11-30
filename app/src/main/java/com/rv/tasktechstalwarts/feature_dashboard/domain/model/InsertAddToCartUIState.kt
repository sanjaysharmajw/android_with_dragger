package com.rv.tasktechstalwarts.feature_dashboard.domain.model



sealed class InsertAddToCartUIState {
    data class IsLoading(val isLoading: Boolean) : InsertAddToCartUIState()
    data class OnSuccess(val onSuccess: Long) : InsertAddToCartUIState()
    data class OnFailure(val onFailure: String) : InsertAddToCartUIState()
}