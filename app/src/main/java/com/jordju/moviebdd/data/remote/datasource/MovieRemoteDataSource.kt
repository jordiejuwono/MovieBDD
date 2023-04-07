package com.jordju.moviebdd.data.remote.datasource

import com.jordju.moviebdd.data.remote.model.MovieListResponse
import com.jordju.moviebdd.data.remote.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRemoteDataSource {

    suspend fun getTopRatedMoviesList(): Flow<MovieListResponse>

}

class MovieRemoteDataSourceImpl(private val apiService: ApiService): MovieRemoteDataSource {

    override suspend fun getTopRatedMoviesList(): Flow<MovieListResponse> = flow {
        emit(apiService.getTopRatedMovieList())
    }

}