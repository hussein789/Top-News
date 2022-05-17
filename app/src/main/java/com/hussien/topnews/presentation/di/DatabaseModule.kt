package com.hussien.topnews.presentation.di

import android.app.Application
import androidx.room.Room
import com.hussien.topnews.data.db.ArticleDao
import com.hussien.topnews.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideArticleDatabase(app:Application):ArticleDatabase{
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            "article_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provide(articleDatabase: ArticleDatabase):ArticleDao{
        return articleDatabase.getArticleDao()
    }
}