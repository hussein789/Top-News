package com.hussien.topnews.presentation.di

import com.hussien.topnews.data.repository.NewsRepositoryImpl
import com.hussien.topnews.data.repository.dataSource.NewsLocalDataSource
import com.hussien.topnews.data.repository.dataSource.NewsRemoteDataSource
import com.hussien.topnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provide(newsRemoteDataSource: NewsRemoteDataSource,newsLocalDataSource: NewsLocalDataSource):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
    }
}