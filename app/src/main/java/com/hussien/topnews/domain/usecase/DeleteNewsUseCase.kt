package com.hussien.topnews.domain.usecase

import com.hussien.topnews.data.model.Article
import com.hussien.topnews.domain.repository.NewsRepository

class DeleteNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}