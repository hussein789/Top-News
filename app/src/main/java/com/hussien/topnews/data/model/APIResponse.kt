package com.hussien.topnews.data.model


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("articles")
    val articles: List<Article> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0
)