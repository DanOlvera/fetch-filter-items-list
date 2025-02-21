package com.example.fetchitemslist.feature_items_list.states

import com.example.fetchitemslist.feature_items_list.model.data.FetchItemsResponse

sealed class FetchState {
    data object Loading: FetchState()
    data class Success(val data: FetchItemsResponse): FetchState()
    data class Error(val message: String): FetchState()
}