package com.example.fetchitemslist.feature_items_list.model.repo

import com.example.fetchitemslist.feature_items_list.model.network.FetchApiService
import kotlinx.coroutines.flow.Flow

class FetchRepository(private val apiService: FetchApiService) {

    fun getFetch(): Flow<>
}