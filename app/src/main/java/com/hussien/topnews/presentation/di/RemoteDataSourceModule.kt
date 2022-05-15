package com.hussien.topnews.presentation.di

import com.hussien.topnews.data.repository.dataSource.NewsRemoteDataSource
import com.hussien.topnews.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsNewsRemoteDataSource(newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl):NewsRemoteDataSource
}