package com.example.fetchitemslist.feature_items_list.model.network

import com.example.fetchitemslist.feature_items_list.model.utils.ApiUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FetchApiClient {

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val okhttp = OkHttpClient.Builder().apply {
        addInterceptor(logging)
    }

    val apiService: FetchApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiUtils.BASE_URL)
            .client(okhttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchApiService::class.java)
    }
}