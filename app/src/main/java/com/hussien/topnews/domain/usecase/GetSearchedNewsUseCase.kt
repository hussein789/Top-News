package com.hussien.topnews.domain.usecase

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.util.Resource
import com.hussien.topnews.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery:String):Resource<APIResponse>{
        return newsRepository.getSearchedNews(searchQuery)
    }
}