package com.example.fetchitemslist.feature_items_list.states

sealed class BaseUiState<out T> {
    data object Loading: BaseUiState<Nothing>()
    data class Success<out T>(val data: T): BaseUiState<T>()
    data class Error<out T>(val message: T): BaseUiState<T>()
}