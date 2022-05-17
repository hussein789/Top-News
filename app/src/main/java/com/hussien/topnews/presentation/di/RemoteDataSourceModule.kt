package com.hussien.topnews.presentation.di

import com.hussien.topnews.data.api.NewsApiService
import com.hussien.topnews.data.repository.dataSource.NewsRemoteDataSource
import com.hussien.topnews.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun providesNewsRemoteDataSource(newsApiService: NewsApiService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}