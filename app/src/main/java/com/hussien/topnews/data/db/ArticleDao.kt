package com.hussien.topnews.data.db

import androidx.room.*
import com.hussien.topnews.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM article_table")
    fun getArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}