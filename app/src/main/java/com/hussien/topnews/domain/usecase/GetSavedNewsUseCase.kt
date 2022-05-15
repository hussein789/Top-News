package com.hussien.topnews.domain.usecase

import com.hussien.topnews.data.model.Article
import com.hussien.topnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}