package com.example.fetchitemslist.feature_items_list.intent

sealed class FetchItemsIntent {
    data object FetchData: FetchItemsIntent()
}