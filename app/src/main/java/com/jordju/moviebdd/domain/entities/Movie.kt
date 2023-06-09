package com.jordju.moviebdd.domain.entities

data class Movie(
    var backdropPath: String,
    var id: Int,
    var originalTitle: String,
    var overview: String,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double,
)