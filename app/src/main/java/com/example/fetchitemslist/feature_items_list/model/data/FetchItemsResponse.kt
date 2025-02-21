package com.example.fetchitemslist.feature_items_list.model.data

import com.google.gson.annotations.SerializedName

data class FetchItemsResponse (

    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("listId" ) var listId : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null

)