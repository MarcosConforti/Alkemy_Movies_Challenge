package com.example.alkemymovieschallenge.data.repository

import com.example.alkemymovieschallenge.data.api.APIService
import com.example.alkemymovieschallenge.data.database.dao.MoviesDao
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.database.entities.toMovieDataBase
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class MoviesRepository @Inject constructor(
    private val api: APIService,
    private val moviesDao: MoviesDao
) {
    fun getPopularMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {   //recupero las peliculas
            try {
                var moviesApi = api.getPopularMovies().results
               if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainModel() }))
                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                   moviesDb.collect { dbMovies ->
                       emit(NetworkState.Success(dbMovies))
                   }
                }

            } catch (e: Throwable) {
               emit(NetworkState.Error(e))
            }
        }
    }

     fun getTopRatedMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getTopRatedMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainModel() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    moviesDb.collect { dbMovies ->
                        emit(NetworkState.Success(dbMovies))
                    }
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }

     fun getUpComingMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getUpComingMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainModel() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    moviesDb.collect { dbMovies ->
                        emit(NetworkState.Success(dbMovies))
                    }
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }

     fun getNowPlayingMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getNowPlayingMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainModel() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    moviesDb.collect { dbMovies ->
                        emit(NetworkState.Success(dbMovies))
                    }
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }

     fun getAllMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getAllMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainModel() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    moviesDb.collect { dbMovies ->
                        emit(NetworkState.Success(dbMovies))
                    }
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }
     fun getMoviesFromDataBase(): Flow<List<DomainModel>> {
        return moviesDao.getAllMovies(). map {
            response ->
            response.map { it.toDomainModel() }
        }
    }

    suspend fun insertMovies(movies: List<MoviesEntities>) =
        moviesDao.insertAll(movies)

    suspend fun cleanList() =
        moviesDao.deleteAllMovies()
}