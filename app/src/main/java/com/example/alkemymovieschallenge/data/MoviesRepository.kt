package com.example.alkemymovieschallenge.data

import com.example.alkemymovieschallenge.data.database.dao.MoviesDao
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.model.MoviesModel
import com.example.alkemymovieschallenge.data.network.MoviesService
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.model.toDomainMovie
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class MoviesRepository @Inject constructor(
    private val api: MoviesService,
    private val moviesDao: MoviesDao
) {
    suspend fun getPopularMoviesFromApi(): List<DomainModel> {
        //recupero las peliculas
        val response: List<MoviesModel> = api.getPopularMovies()
        //mapeo
        return response.map { it.toDomainMovie() }
    }

    suspend fun getLatestMoviesFromApi(): List<DomainModel> {

        val response: List<MoviesModel> = api.getLatestMovies()

        return response.map { it.toDomainMovie() }
    }

    suspend fun getTopRatedMoviesFromApi(): List<DomainModel> {

        val response: List<MoviesModel> = api.getTopRatedMovies()

        return response.map { it.toDomainMovie() }
    }

    suspend fun getUpComingMoviesFromApi(): List<DomainModel> {

        val response: List<MoviesModel> = api.getUpComingMovies()

        return response.map { it.toDomainMovie() }
    }

    suspend fun getNowPlayingMoviesFromApi(): List<DomainModel> {

        val response: List<MoviesModel> = api.getNowPlayingMovies()

        return response.map { it.toDomainMovie() }
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