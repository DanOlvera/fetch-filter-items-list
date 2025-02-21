package com.example.fetchitemslist.feature_items_list.model.network

import com.example.fetchitemslist.feature_items_list.model.data.FetchItemsResponse
import retrofit2.http.GET

interface FetchApiService {

    @GET("hiring.json")
    suspend fun getItems(): List<FetchItemsResponse>
}