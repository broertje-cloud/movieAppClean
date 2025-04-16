package me.Angelo.movieapp.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.Angelo.movieapp.domain.models.Movie
import me.Angelo.movieapp.service.MovieService

class MovieSearchController(
    private val coroutineScope: CoroutineScope,
    private val movieService: MovieService = MovieService()
) {
    fun searchMovies(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onError: (String) -> Unit
    ) {
        coroutineScope.launch {
            try {
                val results = movieService.searchMovies(query)
                onSuccess(results)
            } catch (e: Exception) {
                onError("Fout bij ophalen: ${e.localizedMessage}")
            }
        }
    }
}
