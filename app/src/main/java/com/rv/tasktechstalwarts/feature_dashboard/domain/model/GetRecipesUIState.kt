package com.rv.tasktechstalwarts.feature_dashboard.domain.model

sealed class GetRecipesUIState {
    data class IsLoading(val isLoading: Boolean) : GetRecipesUIState()
    data class OnSuccess(val onSuccess: GetRecipesResponse) : GetRecipesUIState()
    data class OnFailure(val onFailure: String) : GetRecipesUIState()
}