import me.Angelo.movieapp.data.remote.MovieRemoteDataSource
import me.Angelo.movieapp.domain.usecase.SearchMoviesUseCase
import me.Angelo.movieapp.presentation.movie_list.MovieListViewModel
import me.Angelo.movieapp.service.MovieService
import me.Angelo.movieapp.data.repository.MovieRepositoryImpl


object ViewModelProvider {
    fun provideMovieListViewModel(): MovieListViewModel {
        val httpClient = MovieService().client // of jouw manier om de API aan te roepen
        val apiKey = "62e795df"
        val api = MovieRemoteDataSource(httpClient, apiKey)
        val repo = MovieRepositoryImpl(api)
        val useCase = SearchMoviesUseCase(repo)
        return MovieListViewModel(useCase)
    }
}
