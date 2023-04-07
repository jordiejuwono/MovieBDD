package com.jordju.moviebdd.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordju.moviebdd.data.Resource
import com.jordju.moviebdd.domain.entities.MovieList
import com.jordju.moviebdd.domain.usecase.GetTopRatedMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetTopRatedMoviesUseCase): ViewModel() {

    private val _movieState: MutableStateFlow<Resource<MovieList>> = MutableStateFlow(Resource.Loading())
    val movieState get() = _movieState

    fun getTopRatedMovies() {
        viewModelScope.launch {
            useCase.execute().collect {
                _movieState.value = it
            }
        }
    }

}