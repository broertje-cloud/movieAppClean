package me.Angelo.movieapp.domain.repository

import me.Angelo.movieapp.domain.models.Movie
import me.Angelo.movieapp.domain.models.MovieDetail

interface MovieRepository {
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovieDetail(imdbId: String): MovieDetail
}
