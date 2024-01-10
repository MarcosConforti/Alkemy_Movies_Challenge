package com.example.alkemymovieschallenge.movies.data.repository

import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.toDomainModel
import com.example.alkemymovieschallenge.movies.data.api.APIMovieService
import com.example.alkemymovieschallenge.movies.data.api.model.MoviesModel
import com.example.alkemymovieschallenge.movies.data.database.MoviesDao
import com.example.alkemymovieschallenge.movies.data.database.toMovieDataBase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class MoviesRepository @Inject constructor(
    private val api: APIMovieService,
    private val moviesDao: MoviesDao
) : BaseMovieRepository<MoviesModel>(api, moviesDao) {

    fun getPopularMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return getFromApiAndDatabase(
            apiCall = { api.getPopularMovies() },
            mapApiResponse = { it.results },
            mapToEntity = { it.toMovieDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }

    fun getTopRatedMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return getFromApiAndDatabase(
            apiCall = { api.getTopRatedMovies() },
            mapApiResponse = { it.results },
            mapToEntity = { it.toMovieDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }
    fun getUpComingMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return getFromApiAndDatabase(
            apiCall = { api.getUpComingMovies() },
            mapApiResponse = { it.results },
            mapToEntity = { it.toMovieDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }
    fun getNowPlayingMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return getFromApiAndDatabase(
            apiCall = { api.getNowPlayingMovies() },
            mapApiResponse = { it.results },
            mapToEntity = { it.toMovieDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }

    fun getAllMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return getFromApiAndDatabase(
            apiCall = { api.getAllMovies() },
            mapApiResponse = { it.results },
            mapToEntity = { it.toMovieDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }
}