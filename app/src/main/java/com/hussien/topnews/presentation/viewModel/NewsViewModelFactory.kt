package com.hussien.topnews.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hussien.topnews.domain.usecase.GetNewsHeadlinesUseCase
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsViewModel::class.java))
            return NewsViewModel(getNewsHeadlinesUseCase) as T
        throw IllegalArgumentException("unknown view model found")
    }
}