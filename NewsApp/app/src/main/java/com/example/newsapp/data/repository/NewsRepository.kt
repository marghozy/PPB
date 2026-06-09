package com.example.newsapp.data.repository

import com.example.newsapp.data.api.RetrofitClient

class NewsRepository {

    suspend fun getNews() =

        RetrofitClient.apiService
            .getTopHeadlines(
                apiKey = "bf16a6a3e0e240e5b78056cfe352dc45"
            )

    suspend fun searchNews(
        query: String
    ) =

        RetrofitClient.apiService
            .searchNews(
                query = query,
                apiKey = "bf16a6a3e0e240e5b78056cfe352dc45"
            )
}