package com.jordju.moviebdd.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.domain.entities.MovieList
import com.jordju.moviebdd.domain.usecase.GetNowPlayingMoviesUseCase
import com.jordju.moviebdd.domain.usecase.GetTopRatedMoviesUseCase
import com.jordju.moviebdd.domain.usecase.GetUpcomingMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainViewModel(
    private val topRatedUseCase: GetTopRatedMoviesUseCase,
    private val upcomingUseCase: GetUpcomingMoviesUseCase,
    private val nowPlayingUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    init {
        getTopRatedMovies()
        getUpcomingMovies()
        getNowPlayingMovies()
    }

    private val _topRatedState: MutableStateFlow<Resource<MovieList>> =
        MutableStateFlow(Resource.Loading())
    val topRatedState get() = _topRatedState

    private val _upcomingState: MutableStateFlow<Resource<MovieList>> =
        MutableStateFlow(Resource.Loading())
    val upcomingState get() = _upcomingState

    private val _nowPlayingState: MutableStateFlow<Resource<MovieList>> =
        MutableStateFlow(Resource.Loading())
    val nowPlayingState get() = _nowPlayingState

    fun getAllMovies() = combine(
        _topRatedState,
        _upcomingState,
        _nowPlayingState
    ) { topRated, upcoming, nowPlaying ->
        when {
            topRated is Resource.Loading || upcoming is Resource.Loading || nowPlaying is Resource.Loading ->
                Resource.Loading()
            topRated is Resource.Error || upcoming is Resource.Error || nowPlaying is Resource.Error ->
                Resource.Error("")
            else ->
                Resource.Success(listOf(topRated, upcoming, nowPlaying))
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            topRatedUseCase.execute().collect {
                _topRatedState.value = it
            }
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            upcomingUseCase.execute().collect {
                _upcomingState.value = it
            }
        }
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            nowPlayingUseCase.execute().collect {
                _nowPlayingState.value = it
            }
        }
    }

}