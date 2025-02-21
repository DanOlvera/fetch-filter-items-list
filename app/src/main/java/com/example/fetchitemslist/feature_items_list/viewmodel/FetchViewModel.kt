package com.example.fetchitemslist.feature_items_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchitemslist.feature_items_list.intent.FetchItemsIntent
import com.example.fetchitemslist.feature_items_list.model.repo.FetchRepository
import com.example.fetchitemslist.feature_items_list.states.FetchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FetchViewModel(private val repository: FetchRepository): ViewModel() {

    private val _items = MutableStateFlow<FetchUiState>(FetchUiState.Loading)
    val item: StateFlow<FetchUiState> = _items

    fun handleIntent(intent: FetchItemsIntent) {
        when(intent) {
            is FetchItemsIntent.FetchData -> {
                fetchItems()
            }
        }
    }

    private fun fetchItems() {
        viewModelScope.launch {
            repository.getFetch().collect { fetch ->
                try {
                    _items.value = fetch
                } catch (e: Exception) {
                    FetchUiState.Error(e.message ?: "Unknown Error")
                }
            }
        }
    }
}