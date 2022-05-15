package com.hussien.topnews.data.repository

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.data.repository.dataSource.NewsRemoteDataSource
import com.hussien.topnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(page:Int): APIResponse {
        return newsRemoteDataSource.getTopHeadlines("us",page)
    }

    override suspend fun getSearchedNews(searchQuery: String): APIResponse {
    }

    override suspend fun saveNews(article: Article) {
    }

    override suspend fun deleteNews(article: Article) {
    }

    override fun getSavedNews(): Flow<List<Article>> {
    }

}