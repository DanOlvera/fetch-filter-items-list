package com.example.fetchitemslist.feature_items_list.states

import com.example.fetchitemslist.feature_items_list.model.data.FetchItemsResponse

sealed class FetchUiState {
    data object Loading: FetchUiState()
    data class Success(val data: List<FetchItemsResponse>): FetchUiState()
    data class Error(val message: String): FetchUiState()
}