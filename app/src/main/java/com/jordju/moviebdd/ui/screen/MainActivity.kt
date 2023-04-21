package com.jordju.moviebdd.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

    LaunchedEffect(Unit) {
        viewModel.getAllMovies()
    }

    val movieState = viewModel.getAllMovies().collectAsState(initial = Resource.Loading())

    val upcomingMovies by viewModel.upcomingState.collectAsState()
    val topRatedMovies by viewModel.topRatedState.collectAsState()
    val nowPlayingMovies by viewModel.nowPlayingState.collectAsState()

    var headerMovie by remember {
        mutableStateOf<Movie?>(null)
    }

    movieState.value.let { uiState ->
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
                val topRatedResults = topRatedMovies.data
                val upcomingResults = upcomingMovies.data
                val nowPlayingResults = nowPlayingMovies.data

                if (headerMovie == null && (topRatedResults?.results?.isNotEmpty() == true)) {
                    headerMovie = topRatedResults.results.random()
                }

                LazyColumn {
                    item {
                        headerMovie?.let { MovieHeader(movieItem = it) }
                    }
                    item {
                        MovieTitle(title = "Now Playing")
                    }
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            nowPlayingResults?.results?.let {
                                items(it) { movie ->
                                    HorizontalMovieItem(
                                        imageUrl = movie.posterPath,
                                        movieTitle = movie.originalTitle
                                    )
                                }
                            }
                        }
                    }
                    item {
                        MovieTitle(title = "Upcoming Movies")
                    }
                    upcomingResults?.results?.let {
                        items(it) { movie ->
                            MovieItem(
                                imageUrl = movie.posterPath,
                                movieTitle = movie.originalTitle,
                                movieDescription = movie.overview,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).height(130.dp)
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

@Composable
fun HorizontalMovieItem(imageUrl: String, movieTitle: String) {
    Column(
        modifier = Modifier
            .height(240.dp)
            .width(120.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = movieTitle,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(movieTitle, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}