package com.hussien.topnews.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hussien.topnews.domain.usecase.*
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val searchedNewsUseCase: GetSearchedNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsViewModel::class.java))
            return NewsViewModel(getNewsHeadlinesUseCase,searchedNewsUseCase,getSavedNewsUseCase,saveNewsUseCase,deleteNewsUseCase) as T
        throw IllegalArgumentException("unknown view model found")
    }
}