package com.jordju.moviebdd.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.ui.components.MovieItem
import com.jordju.moviebdd.ui.theme.MovieBDDTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieBDDTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieContent()
                }
            }
        }
    }
}

@Composable
fun MovieContent(
    viewModel: MainViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getTopRatedMovies()
    }

    val movieState by viewModel.movieState.collectAsState()

    movieState.let { uiState ->
        when (uiState) {
            is Resource.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }
            is Resource.Success -> {
                LazyColumn(contentPadding = PaddingValues(8.dp)) {
                    movieState.data?.results?.let {
                        items(it) { data ->
                            MovieItem(
                                imageUrl = data.posterPath,
                                movieTitle = data.originalTitle,
                                movieDescription = data.overview
                            )
                        }
                    }
                }
            }
            is Resource.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = uiState.message.toString())
                }
            }
        }
    }


}