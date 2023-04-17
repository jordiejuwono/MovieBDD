package com.jordju.moviebdd.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.domain.entities.Movie
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

    LaunchedEffect(viewModel.movieState) {
        viewModel.getTopRatedMovies()
    }

    val movieState by viewModel.movieState.collectAsState()

    var headerMovie by remember {
        mutableStateOf<Movie?>(null)
    }

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
                val moviesResult = movieState.data

                if (headerMovie == null && (moviesResult?.results?.isNotEmpty() == true)) {
                    headerMovie = moviesResult.results.random()
                }

                LazyColumn {
                    moviesResult?.let { movieList ->
                        item {
                            headerMovie?.let { MovieHeader(movieItem = it) }
                        }
                        item {
                            MovieTitle(title = "Top Rated")
                        }
                        items(movieList.results) { movie ->
                            movie.let {
                                MovieItem(
                                    imageUrl = it.posterPath,
                                    movieTitle = it.originalTitle,
                                    movieDescription = it.overview,
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .height(120.dp)
                                )
                            }
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

@Composable
fun MovieHeader(movieItem: Movie) {
    Box {
        AsyncImage(
            model = movieItem.posterPath,
            contentDescription = movieItem.originalTitle,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = movieItem.originalTitle,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            color = Color.White,
            fontSize = 24.sp,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun MovieTitle(title: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier.padding(4.dp)
    )
}
