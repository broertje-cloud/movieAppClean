package me.Angelo.movieapp.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import me.Angelo.movieapp.domain.models.Movie
import me.Angelo.movieapp.domain.models.MovieDetail

class MovieRemoteDataSource(private val httpClient: HttpClient, private val apiKey: String) : MovieApi {

    override suspend fun searchMovies(query: String): List<Movie> {
        val response: HttpResponse = httpClient.get("https://www.omdbapi.com/") {
            parameter("s", query)
            parameter("apikey", apiKey)
        }
        val result = response.body<SearchResponse>()
        return result.movies ?: emptyList()
    }

    override suspend fun getMovieDetail(imdbID: String): MovieDetail {
        val response: HttpResponse = httpClient.get("https://www.omdbapi.com/") {
            parameter("i", imdbID)
            parameter("apikey", apiKey)
        }
        return response.body()
    }
}

// Dit model is alleen intern nodig voor parsing van de search response
@kotlinx.serialization.Serializable
private data class SearchResponse(
    @kotlinx.serialization.SerialName("Search") val movies: List<Movie>? = null
)
