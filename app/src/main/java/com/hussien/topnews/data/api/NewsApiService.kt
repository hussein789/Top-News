package com.hussien.topnews.data.api

import com.hussien.topnews.BuildConfig
import com.hussien.topnews.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String,
        @Query("page") page:Int,
        @Query("apiKey") apiKey:String = BuildConfig.API_KEY
    ):APIResponse

    @GET("v2/top-headlines")
    suspend fun searchTopHeadlines(
        @Query("country") country:String,
        @Query("page") page:Int,
        @Query("q") searchQuery:String,
        @Query("apiKey") apiKey:String = BuildConfig.API_KEY,
    ):APIResponse

}