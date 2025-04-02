package me.Angelo.movieapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import me.Angelo.movieapp.models.Movie
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import me.Angelo.movieapp.service.MovieService

@Composable
fun MovieSearchScreen() {
    var query by remember { mutableStateOf("") }
    var movies by remember { mutableStateOf(listOf<Movie>()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Zoek een film") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    errorMessage = null
                    try {
                        val results = MovieService().searchMovies(query)
                        movies = results
                    } catch (e: Exception) {
                        errorMessage = "Fout bij ophalen: ${e.localizedMessage}"
                        movies = emptyList()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Zoeken")
        }

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(movies) { movie ->
                Column(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                ) {
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Text(
                        text = "${movie.title} (${movie.year})",
                        style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
