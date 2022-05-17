package com.hussien.topnews.domain.usecase

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.domain.repository.NewsRepository
import dagger.hilt.InstallIn
import javax.inject.Inject


class GetSearchedNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(page:Int,searchQuery:String):APIResponse{
        return newsRepository.getSearchedNews(page,searchQuery)
    }
}