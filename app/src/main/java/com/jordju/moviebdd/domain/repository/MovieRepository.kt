package com.jordju.moviebdd.domain.repository

import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.domain.entities.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getTopRatedMoviesList() : Flow<Resource<MovieList>>
    suspend fun getNowPlayingMovieList() : Flow<Resource<MovieList>>
    suspend fun getUpcomingMovieList() : Flow<Resource<MovieList>>

}