package com.example.alkemymovieschallenge.data.repository

import com.example.alkemymovieschallenge.data.database.dao.MoviesDao
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.database.entities.toMovieDataBase
import com.example.alkemymovieschallenge.data.api.APIService
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.model.toDomainMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class MoviesRepository @Inject constructor(
    private val api: APIService,
    private val moviesDao: MoviesDao
) {
    suspend fun getPopularMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {   //recupero las peliculas
            try {
                var moviesApi = api.getPopularMovies().results
               if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainMovie() }))
                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    emit(NetworkState.Success(moviesDb))
                }

            } catch (e: Throwable) {
               emit(NetworkState.Error(e))
            }
        }
    }

    suspend fun getTopRatedMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getTopRatedtMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainMovie() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    emit(NetworkState.Success(moviesDb))
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }

    suspend fun getUpComingMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getUpComingMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainMovie() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    emit(NetworkState.Success(moviesDb))
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }

    suspend fun getNowPlayingMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getNowPlayingMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainMovie() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    emit(NetworkState.Success(moviesDb))
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }

    suspend fun getAllMoviesFromApi(): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                var moviesApi = api.getAllMovies().results
                if (moviesApi.isNotEmpty()) {
                    cleanList()
                    insertMovies(moviesApi.map { it.toMovieDataBase() })
                    emit(NetworkState.Success(moviesApi.map { it.toDomainMovie() }))

                } else {
                    //si esta vacio, que recupere los datos de la db
                    val moviesDb = getMoviesFromDataBase()
                    emit(NetworkState.Success(moviesDb))
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }

    suspend fun getMoviesFromDataBase(): List<DomainModel> {
        val response = moviesDao.getAllMovies()
        return response.map { it.toDomainMovie() }
    }

    suspend fun insertMovies(movies: List<MoviesEntities>) {
        moviesDao.insertAll(movies)
    }


    suspend fun cleanList() {
        moviesDao.deleteAllMovies()
    }


}