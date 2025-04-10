package me.Angelo.movieapp

import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.Angelo.movieapp.service.MovieService
import androidx.compose.ui.Alignment
import me.Angelo.movieapp.domain.models.MovieDetail


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(imdbID: String, onBack: () -> Unit) {
    var movie by remember { mutableStateOf<MovieDetail?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(imdbID) {
        val service = MovieService()
        movie = service.getMovieDetail(imdbID)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(movie?.title ?: "Film Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Terug")
                    }
                }
            )
        }
    ) { padding ->
        movie?.let {
            Column(modifier = Modifier
                .padding(padding)
                .padding(16.dp)) {
                AsyncImage(
                    model = it.poster,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "${it.title} (${it.year})", style = MaterialTheme.typography.headlineMedium)
                Text(text = "🎬 Genre: ${it.genre}")
                Text(text = "🎥 Director: ${it.director}")
                Text(text = "⭐ Rating: ${it.rating}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.plot)
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}



