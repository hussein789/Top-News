package com.hussien.topnews.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.domain.usecase.GetNewsHeadlinesUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModel() {

    private val topHeadlinesUiState = MutableLiveData<APIResponse>()
    private val loadingUiState = MutableLiveData<Boolean>()
    private val errorUiState = MutableLiveData<String>()

    fun getTopHeadlines(page:Int){
        viewModelScope.launch {
            loadingUiState.value = true
            try {
                val response = getNewsHeadlinesUseCase.execute(page)
                topHeadlinesUiState.value = response
            } catch (ex:Exception){
                errorUiState.value = ex.message
            }
            finally {
                loadingUiState.value = false
            }
        }
    }
}