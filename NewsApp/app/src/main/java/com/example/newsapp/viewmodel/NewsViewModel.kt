package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repository =
        NewsRepository()

    private val _uiState =
        MutableStateFlow<NewsUiState>(
            NewsUiState.Loading
        )

    val uiState =
        _uiState.asStateFlow()

    init {
        loadNews()
    }

    fun loadNews() {

        viewModelScope.launch {

            try {

                _uiState.value =
                    NewsUiState.Loading

                val response =
                    repository.getNews()

                _uiState.value =
                    NewsUiState.Success(
                        response.articles
                    )

            } catch (e: Exception) {

                _uiState.value =
                    NewsUiState.Error(
                        e.message ?: "Unknown Error"
                    )

            }

        }

    }

    fun searchNews(
        query: String
    ) {

        viewModelScope.launch {

            try {

                _uiState.value =
                    NewsUiState.Loading

                val response =
                    repository.searchNews(query)

                _uiState.value =
                    NewsUiState.Success(
                        response.articles
                    )

            } catch (e: Exception) {

                _uiState.value =
                    NewsUiState.Error(
                        e.message ?: "Unknown Error"
                    )

            }

        }

    }
}