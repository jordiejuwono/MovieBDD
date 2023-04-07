package com.jordju.moviebdd.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jordju.moviebdd.ui.theme.MovieBDDTheme

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    movieTitle: String,
    movieDescription: String,
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            contentDescription = movieTitle,
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .padding(3.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(text = movieTitle, fontWeight = FontWeight.Bold)
            Text(text = movieDescription, maxLines = 3)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieBDDTheme {
        MovieItem(
            imageUrl = "https://media.21cineplex.com/webcontent/gallery/pictures/167878663353703_405x594.jpg",
            movieTitle = "Mario Bros Movie",
            movieDescription = "Mario Bros Movie Description"
        )
    }
}