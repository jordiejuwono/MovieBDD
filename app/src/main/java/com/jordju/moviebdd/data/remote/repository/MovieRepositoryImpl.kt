package com.jordju.moviebdd.data.remote.repository

import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.data.remote.datasource.MovieRemoteDataSource
import com.jordju.moviebdd.data.remote.mapper.MovieMapper
import com.jordju.moviebdd.domain.entities.MovieList
import com.jordju.moviebdd.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(private val dataSource: MovieRemoteDataSource, private val mapper: MovieMapper): MovieRepository {
    override suspend fun getTopRatedMoviesList(): Flow<Resource<MovieList>> = flow {
        try {
            dataSource.getTopRatedMoviesList().collect { result ->
                emit(Resource.Success(mapper.mapMovieListResponseToEntity(result)))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}