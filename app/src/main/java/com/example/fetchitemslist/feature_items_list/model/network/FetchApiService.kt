package com.example.fetchitemslist.feature_items_list.model.network

import com.example.fetchitemslist.feature_items_list.model.data.FetchItems
import retrofit2.http.GET

interface FetchApiClient {

    @GET("hiring.json")
    suspend fun getItems(): FetchItems
}