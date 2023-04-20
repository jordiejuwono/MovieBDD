package com.jordju.moviebdd.domain.usecase

import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.domain.entities.MovieList
import com.jordju.moviebdd.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetUpcomingMoviesUseCase(private val repository: MovieRepository) {

    suspend fun execute(): Flow<Resource<MovieList>> = repository.getUpcomingMovieList()

}