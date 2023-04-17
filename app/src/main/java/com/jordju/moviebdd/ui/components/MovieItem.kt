package com.jordju.moviebdd.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .width(90.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = movieTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movieDescription,
                maxLines = 3,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray
            )
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