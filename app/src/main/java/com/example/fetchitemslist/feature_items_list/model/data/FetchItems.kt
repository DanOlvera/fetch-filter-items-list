package com.example.fetchitemslist.feature_items_list.model

data class ExampleJson2KtKotlin (

    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("listId" ) var listId : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null

)