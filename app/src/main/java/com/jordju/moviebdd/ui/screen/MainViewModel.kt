package com.jordju.moviebdd.ui.screen

import androidx.lifecycle.ViewModel
import com.jordju.moviebdd.domain.usecase.GetTopRatedMoviesUseCase

class MainViewModel(private val useCase: GetTopRatedMoviesUseCase): ViewModel() {
}