package com.hussien.topnews.domain.repository

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository{
    suspend fun getNewsHeadlines(page:Int):APIResponse
    suspend fun getSearchedNews(page:Int,searchQuery:String):APIResponse
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}