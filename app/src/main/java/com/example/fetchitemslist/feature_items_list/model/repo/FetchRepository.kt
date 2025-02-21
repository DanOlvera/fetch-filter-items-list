package com.example.fetchitemslist.feature_items_list.model.repo

import com.example.fetchitemslist.feature_items_list.model.network.FetchApiService
import com.example.fetchitemslist.feature_items_list.states.FetchUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchRepository(private val apiService: FetchApiService) {

    fun getFetch(): Flow<FetchUiState> = flow {
        try {
            emit(FetchUiState.Loading)
            val response = apiService.getItems()
            emit(FetchUiState.Success(response))
        } catch (e: Exception) {
            emit(FetchUiState.Error(e.message ?: "Unknown Error"))
        }
    }
}