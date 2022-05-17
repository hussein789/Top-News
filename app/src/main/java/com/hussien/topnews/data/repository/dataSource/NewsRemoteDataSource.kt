package com.hussien.topnews.data.repository.dataSource

import com.hussien.topnews.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country:String,page:Int): APIResponse
    suspend fun searchTopHeadlines(country:String,page:Int,searchQuery:String):APIResponse
}