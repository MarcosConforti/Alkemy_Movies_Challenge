package com.example.alkemymovieschallenge.data

import com.example.alkemymovieschallenge.data.database.dao.MoviesDao
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.model.MoviesModel
import com.example.alkemymovieschallenge.data.network.MoviesService
import com.example.alkemymovieschallenge.domain.model.DomainModel
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class MoviesRepository @Inject constructor(
    private val api: MoviesService,
    private val moviesDao: MoviesDao
) {
    suspend fun getMoviesFromApi():List<DomainModel>{
        //recupero los tragos
        val response: List<MoviesModel> = api.getMovies()
        //mapeo
        return response.map { it.toDomain() }
    }
    suspend fun getMoviesFromDataBase():List<DomainModel>{
        val response = moviesDao.getAllMovies()
        return response.map{it.toDomain()}
    }
    suspend fun insertMovies(movies: List<MoviesEntities>) {
        moviesDao.insertAll(movies)
    }
    suspend fun insertOnlyMovie(movie: MoviesEntities){
        moviesDao.insert(movie)
    }

    suspend fun cleanListDrink() {
        moviesDao.deleteAllMovies()
    }
}