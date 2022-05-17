package com.hussien.topnews.data.repository.dataSource

import com.hussien.topnews.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun addArticle(article: Article)
    fun getArticles(): Flow<List<Article>>
    suspend fun deleteArticle(article: Article)
}