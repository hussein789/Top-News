package com.hussien.topnews.data.repository

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.data.repository.dataSource.NewsLocalDataSource
import com.hussien.topnews.data.repository.dataSource.NewsRemoteDataSource
import com.hussien.topnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(page:Int): APIResponse {
        return newsRemoteDataSource.getTopHeadlines("us",page)
    }

    override suspend fun getSearchedNews(page:Int,searchQuery: String): APIResponse {
        return newsRemoteDataSource.searchTopHeadlines("us",page,searchQuery)
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.addArticle(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticle(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getArticles()
    }

}