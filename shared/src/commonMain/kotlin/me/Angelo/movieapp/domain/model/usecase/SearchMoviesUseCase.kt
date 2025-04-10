package me.Angelo.movieapp.domain.usecase

import me.Angelo.movieapp.data.repository.MovieRepositoryImpl
import me.Angelo.movieapp.domain.models.Movie

class SearchMoviesUseCase(
    private val repository: MovieRepositoryImpl
) {
    suspend operator fun invoke(query: String): List<Movie> {
        return repository.searchMovies(query)
    }
}
