package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import android.net.Uri
import com.example.newsapp.ui.components.NewsCard
import com.example.newsapp.viewmodel.NewsUiState
import com.example.newsapp.viewmodel.NewsViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: NewsViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {

        is NewsUiState.Loading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

        is NewsUiState.Error -> {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Gagal Memuat Berita"
                )

                Text(
                    text = (uiState as NewsUiState.Error).message
                )

                Button(
                    onClick = {
                        viewModel.loadNews()
                    }
                ) {
                    Text("Coba Lagi")
                }

            }

        }

        is NewsUiState.Success -> {

            var query by remember {
                mutableStateOf("")
            }

            val articles =
                (uiState as NewsUiState.Success).articles

            Column {

                OutlinedTextField(
                    value = query,
                    onValueChange = {
                        query = it
                    },
                    label = {
                        Text("Cari Berita")
                    }
                )

                Button(
                    onClick = {
                        viewModel.searchNews(query)
                    }
                ) {
                    Text("Search")
                }

                LazyColumn {

                    items(articles) { article ->

                        NewsCard(
                            article = article,
                            onClick = {

                                val title =
                                    Uri.encode(article.title)

                                val imageUrl =
                                    Uri.encode(article.urlToImage ?: "")

                                val description =
                                    Uri.encode(article.description ?: "")

                                navController.navigate(
                                    "detail/$title/$imageUrl/$description"
                                )
                            }
                        )
                    }
                }
            }
        }

    }

}