package me.Angelo.movieapp

import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.encodeURL(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

fun String.decodeURL(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "search") {
                composable("search") {
                    movieSearchScreen(onMovieClick = { movie ->
                        navController.navigate("detail/${movie.imdbID}")
                    })
                }
                composable("detail/{imdbID}") { backStackEntry ->
                    val imdbID = backStackEntry.arguments?.getString("imdbID") ?: ""
                    MovieDetailScreen(imdbID = imdbID, onBack = { navController.popBackStack() })
                }

            }
        }
    }
}

