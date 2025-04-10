package me.Angelo.movieapp.presentation.movie_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.Angelo.movieapp.domain.usecase.SearchMoviesUseCase


class MovieListViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    val state = mutableStateOf(MovieListState())

    fun searchMovies(query: String) {
        state.value = state.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = searchMoviesUseCase(query)
            state.value = state.value.copy(
                movies = result,
                isLoading = false
            )
        }
    }
}
