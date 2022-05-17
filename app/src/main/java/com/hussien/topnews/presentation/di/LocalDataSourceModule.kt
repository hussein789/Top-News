package com.hussien.topnews.presentation.di

import com.hussien.topnews.data.db.ArticleDao
import com.hussien.topnews.data.repository.dataSource.NewsLocalDataSource
import com.hussien.topnews.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    fun provides(articleDao: ArticleDao):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDao)
    }
}