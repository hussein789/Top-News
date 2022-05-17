package com.hussien.topnews.data.repository.dataSourceImpl

import com.hussien.topnews.data.db.ArticleDao
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {
    override suspend fun addArticle(article: Article) {
        articleDao.insertArticle(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return articleDao.getArticles()
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }
}