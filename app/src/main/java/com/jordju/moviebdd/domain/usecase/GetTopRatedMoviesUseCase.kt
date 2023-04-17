package com.jordju.moviebdd.domain.usecase

import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.domain.entities.MovieList
import com.jordju.moviebdd.domain.presentation.ViewItems
import com.jordju.moviebdd.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopRatedMoviesUseCase(private val repository: MovieRepository) {

    suspend fun execute(): Flow<Resource<MovieList>> = repository.getTopRatedMoviesList()
}