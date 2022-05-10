package com.hussien.topnews.domain.repository

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository{
    suspend fun getNewsHeadlines():Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery:String):Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}