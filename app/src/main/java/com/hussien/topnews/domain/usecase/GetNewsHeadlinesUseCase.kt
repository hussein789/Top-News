package com.hussien.topnews.domain.usecase

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.util.Resource
import com.hussien.topnews.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute():Resource<APIResponse>{
        return newsRepository.getNewsHeadlines()
    }
}