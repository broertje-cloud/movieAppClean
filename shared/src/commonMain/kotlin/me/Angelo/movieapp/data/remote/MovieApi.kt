package me.Angelo.movieapp.data.remote

import me.Angelo.movieapp.domain.models.Movie
import me.Angelo.movieapp.domain.models.MovieDetail

interface MovieApi {
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovieDetail(imdbID: String): MovieDetail
}
