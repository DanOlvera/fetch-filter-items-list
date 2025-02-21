package com.example.fetchitemslist.feature_items_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseViewModelFactory<V: ViewModel>(
    private val viewModelClass: Class<V>,
    private val create: () -> V
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModelClass)) {
            @Suppress("UNCHECKED_CAST")
            return create() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}