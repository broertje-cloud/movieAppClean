package me.Angelo.movieapp

import me.Angelo.movieapp.service.MovieService
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val service = MovieService()
    val results = service.searchMovies("Batman")

    println("Gevonden films:")
    for (movie in results) {
        println("- ${movie.title} (${movie.year})")
    }
}
