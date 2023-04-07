package com.jordju.moviebdd.data.remote.mapper

import com.jordju.moviebdd.data.remote.model.MovieListResponse
import com.jordju.moviebdd.data.remote.model.MovieResponse
import com.jordju.moviebdd.domain.entities.Movie
import com.jordju.moviebdd.domain.entities.MovieList

object MovieMapper {
    fun mapMovieListResponseToEntity(
        data: MovieListResponse
    ): MovieList {
        return MovieList(
            results = mapMovieResponseToEntity(data.results)
        )
    }

    private fun mapMovieResponseToEntity(
        data: List<MovieResponse?>?
    ): List<Movie> {
        val newList = ArrayList<Movie>()
        data?.map { result ->
            val movie = Movie(
                backdropPath = result?.backdropPath ?: "",
                id = result?.id ?: 0,
                originalTitle = result?.originalTitle ?: "",
                overview = result?.overview ?: "",
                posterPath = result?.posterPath ?: "",
                releaseDate = result?.releaseDate ?: "",
                title = result?.title ?: "",
                voteAverage = result?.voteAverage ?: 0.0,
            )
            newList.add(movie)
        }
        return newList
    }
}