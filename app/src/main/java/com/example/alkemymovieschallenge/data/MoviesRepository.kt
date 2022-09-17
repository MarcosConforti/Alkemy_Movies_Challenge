package com.example.alkemymovieschallenge.data

import com.example.alkemymovieschallenge.data.database.dao.MoviesDao
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.database.entities.toMovieDataBase
import com.example.alkemymovieschallenge.data.network.APIService
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.model.toDomainMovie
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class MoviesRepository @Inject constructor(
    private val api: APIService,
    private val moviesDao: MoviesDao
) {
    suspend fun getPopularMoviesFromApi(): NetworkState<List<DomainModel>> =
        //recupero las peliculas
       try {
            var moviesApi = api.getPopularMovies().results
            if (moviesApi.isNotEmpty()) {
                cleanList()
                insertMovies(moviesApi.map { it.toMovieDataBase() })
                NetworkState.Success(moviesApi.map { it.toDomainMovie() })

            } else {
                //si esta vacio, que recupere los datos de la db
                val moviesDb = getMoviesFromDataBase()
                NetworkState.Success(moviesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }

    suspend fun getTopRatedMoviesFromApi(): NetworkState<List<DomainModel>> =

        try {
            var moviesApi = api.getTopRatedtMovies().results
            if (moviesApi.isNotEmpty()) {
                cleanList()
                insertMovies(moviesApi.map { it.toMovieDataBase() })
                NetworkState.Success(moviesApi.map { it.toDomainMovie() })

            } else {
                //si esta vacio, que recupere los datos de la db
                val moviesDb = getMoviesFromDataBase()
                NetworkState.Success(moviesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }

    suspend fun getUpComingMoviesFromApi(): NetworkState<List<DomainModel>> =
        try {
            var moviesApi = api.getUpComingMovies().results
            if (moviesApi.isNotEmpty()) {
                cleanList()
                insertMovies(moviesApi.map { it.toMovieDataBase() })
                NetworkState.Success(moviesApi.map { it.toDomainMovie() })

            } else {
                //si esta vacio, que recupere los datos de la db
                val moviesDb = getMoviesFromDataBase()
                NetworkState.Success(moviesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }

    suspend fun getNowPlayingMoviesFromApi(): NetworkState<List<DomainModel>> =
        try {
            var moviesApi = api.getNowPlayingMovies().results
            if (moviesApi.isNotEmpty()) {
                cleanList()
                insertMovies(moviesApi.map { it.toMovieDataBase() })
                NetworkState.Success(moviesApi.map { it.toDomainMovie() })

            } else {
                //si esta vacio, que recupere los datos de la db
                val moviesDb = getMoviesFromDataBase()
                NetworkState.Success(moviesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }

    suspend fun getMoviesFromDataBase(): List<DomainModel> {
        val response = moviesDao.getAllMovies()
        return response.map { it.toDomainMovie() }
    }

    suspend fun insertMovies(movies: List<MoviesEntities>) {
        moviesDao.insertAll(movies)
    }

    suspend fun insertOnlyMovie(movie: MoviesEntities) {
        moviesDao.insert(movie)
    }

    suspend fun cleanList() {
        moviesDao.deleteAllMovies()
    }


}