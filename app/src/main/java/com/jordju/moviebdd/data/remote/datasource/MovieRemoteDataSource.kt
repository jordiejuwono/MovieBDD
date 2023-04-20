package com.jordju.moviebdd.data.remote.datasource

import com.jordju.moviebdd.data.remote.model.MovieListResponse
import com.jordju.moviebdd.data.remote.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRemoteDataSource {

    suspend fun getTopRatedMoviesList(): Flow<MovieListResponse>
    suspend fun getNowPlayingMovieList(): Flow<MovieListResponse>
    suspend fun getUpcomingMovieList(): Flow<MovieListResponse>

}

class MovieRemoteDataSourceImpl(private val apiService: ApiService): MovieRemoteDataSource {

    override suspend fun getTopRatedMoviesList(): Flow<MovieListResponse> = flow {
        emit(apiService.getTopRatedMovieList())
    }

    override suspend fun getNowPlayingMovieList(): Flow<MovieListResponse> = flow {
        emit(apiService.getNowPlayingMovieList())
    }

    override suspend fun getUpcomingMovieList(): Flow<MovieListResponse> = flow {
        emit(apiService.getUpcomingMovieList())
    }

}