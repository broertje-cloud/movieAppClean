package me.Angelo.movieapp.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import me.Angelo.movieapp.domain.models.Movie
import me.Angelo.movieapp.domain.models.MovieDetail

class MovieService {


    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun searchMovies(query: String): List<Movie> {
        val response: OMDbResponse = client.get("https://www.omdbapi.com/") {
            url {
                parameters.append("apikey", "62e795df")
                parameters.append("s", query)
            }
        }.body()

        return response.search ?: emptyList()
    }

    suspend fun getMovieDetail(imdbID: String): MovieDetail {
        return client.get("https://www.omdbapi.com/") {
            url {
                parameters.append("apikey", "62e795df")
                parameters.append("i", imdbID)
                parameters.append("plot", "full")
            }
        }.body()
    }

    @Serializable
    data class OMDbResponse(
        @SerialName("Search") val search: List<Movie>?
    )
}
