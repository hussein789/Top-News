package com.hussien.topnews.domain.usecase

import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(page:Int):APIResponse{
        return newsRepository.getNewsHeadlines(page)
    }
}