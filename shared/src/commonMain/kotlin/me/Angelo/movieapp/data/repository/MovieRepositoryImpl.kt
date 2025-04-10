package me.Angelo.movieapp.data.repository

import me.Angelo.movieapp.data.remote.MovieApi
import me.Angelo.movieapp.domain.models.Movie
import me.Angelo.movieapp.domain.models.MovieDetail
import me.Angelo.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(private val api: MovieApi) : MovieRepository {
    override suspend fun searchMovies(query: String): List<Movie> {
        return api.searchMovies(query)
    }

    override suspend fun getMovieDetail(imdbID: String): MovieDetail {
        return api.getMovieDetail(imdbID)
    }
}
