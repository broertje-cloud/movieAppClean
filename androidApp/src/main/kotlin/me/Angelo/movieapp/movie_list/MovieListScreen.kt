package me.Angelo.movieapp.presentation.movie_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.material3.Text


@Composable
fun MovieListScreen() {
    val viewModel = remember { ViewModelProvider.provideMovieListViewModel() }
    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.searchMovies("Batman") // Test query
    }

    if (state.isLoading) {
        Text("Bezig met laden...")
    } else {
        Text("Aantal films gevonden: ${state.movies.size}")
    }
}

