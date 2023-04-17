package com.jordju.moviebdd.domain.presentation

import com.jordju.moviebdd.domain.entities.Movie

sealed class ViewItems {
    class HeaderItem(val movieItem: Movie): ViewItems()
    class TitleItem(val title: String): ViewItems()
    class MovieListItems(val movieList: List<Movie>): ViewItems()
}