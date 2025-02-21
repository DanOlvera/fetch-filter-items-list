package com.example.fetchitemslist.feature_items_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchitemslist.feature_items_list.model.repo.FetchRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repo: FetchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FetchViewModel::class.java)) {
            return FetchViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}