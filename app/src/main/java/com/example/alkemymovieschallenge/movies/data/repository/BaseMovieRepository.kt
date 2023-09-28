package com.example.alkemymovieschallenge.movies.data.repository

import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.toDomainModel
import com.example.alkemymovieschallenge.movies.data.api.APIMovieService
import com.example.alkemymovieschallenge.movies.data.database.MoviesDao
import com.example.alkemymovieschallenge.movies.data.database.MoviesEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

open class BaseMovieRepository<T>(
    //dejamos declarada la variable api en caso de necesitar usarla
    private val api: APIMovieService,
    private val dao: MoviesDao
) {
    fun <R> getFromApiAndDatabase(
        apiCall: suspend () -> R,
        mapApiResponse: (R) -> List<T>,
        mapToEntity: (T) -> MoviesEntities,
        mapToDomain: (T) -> DomainModel
    ): Flow<NetworkState<List<DomainModel>>> {
        return flow {
            try {
                val apiResponse = apiCall.invoke()
                val response = mapApiResponse(apiResponse)
                if (response.isNotEmpty()) {
                    cleanList()
                    insertItems(response.map(mapToEntity))
                    emit(NetworkState.Success(response.map(mapToDomain)))
                } else {
                    val itemsDb = getFromDatabase()
                    itemsDb.collect { dbMovies ->
                        emit(NetworkState.Success(dbMovies))
                    }
                }
            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }
    }


    fun getFromDatabase(): Flow<List<DomainModel>> {
        return dao.getAllMovies(). map {
                response ->
            response.map { it.toDomainModel() }
        }
    }

    suspend fun insertItems(items: List<MoviesEntities>) = dao.insertAll(items)

    suspend fun cleanList() = dao.deleteAllMovies()
}