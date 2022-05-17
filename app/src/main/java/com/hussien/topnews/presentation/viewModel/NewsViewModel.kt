package com.hussien.topnews.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hussien.topnews.R
import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.domain.usecase.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val searchedNewsUseCase: GetSearchedNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModel() {

    private var getTopHeadinesJob: Job? = null
    val topHeadlinesUiState = MutableLiveData<List<Article>>()
    val loadingUiState = MutableLiveData<Boolean>()
    val errorUiState = MutableLiveData<String>()
    val snackbarUiState = MutableLiveData<Int>()
    val savedNews = getSavedNewsUseCase.execute().asLiveData()

    private var articles = mutableListOf<Article>()

    private var page = 1
    private var isLastPage = false

    fun getTopHeadlines(){
        getTopHeadinesJob?.cancel()
        getTopHeadinesJob = viewModelScope.launch {
            loadingUiState.value = true
            try {
                val response = getNewsHeadlinesUseCase.execute(page)
                articles.addAll(response.articles)
                topHeadlinesUiState.value = articles
                if (response.articles.isEmpty() || response.articles.size < 20) isLastPage = true
            } catch (ex: Exception) {
                errorUiState.value = ex.message
            } finally {
                loadingUiState.value = false
            }
        }
    }

    fun resetData() {
        page = 1
        articles.clear()
        isLastPage = false
    }

    fun searchTopHeadlines(searchQuery:String) {
        getTopHeadinesJob?.cancel()
        getTopHeadinesJob = viewModelScope.launch {
            delay(2000)
            loadingUiState.value = true
            try {
                val response = searchedNewsUseCase.execute(page,searchQuery)
                articles.clear()
                articles.addAll(response.articles)
                topHeadlinesUiState.value = articles
            } catch (ex:Exception){
                errorUiState.value = ex.message
            }
            loadingUiState.value = false
        }
    }

    fun isLastPage() = isLastPage

    fun loadMore() {
        page ++
        getTopHeadlines()
    }

    fun saveNews(article: Article?){
        if(article == null) return
        viewModelScope.launch {
            try {
                saveNewsUseCase.execute(article)
                snackbarUiState.value = R.string.article_saved_successfully
            } catch (ex:Exception){
                errorUiState.value = ex.message
            }
        }
    }

    fun deleteNews(article: Article){
        viewModelScope.launch {
            try {
                deleteNewsUseCase.execute(article)
            } catch (ex:Exception){
                errorUiState.value = ex.message
            }
        }
    }

}