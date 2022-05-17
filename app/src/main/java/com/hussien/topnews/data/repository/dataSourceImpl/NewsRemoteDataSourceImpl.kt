package com.hussien.topnews.data.repository.dataSourceImpl

import com.hussien.topnews.data.api.NewsApiService
import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country:String,page:Int): APIResponse {
        return newsApiService.getTopHeadlines(country,page)
    }

    override suspend fun searchTopHeadlines(
        country: String,
        page: Int,
        searchQuery: String
    ): APIResponse {
        return newsApiService.searchTopHeadlines(country,page,searchQuery)
    }
}