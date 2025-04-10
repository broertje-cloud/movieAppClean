package me.Angelo.movieapp.presentation.movie_list

import me.Angelo.movieapp.domain.models.Movie

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
