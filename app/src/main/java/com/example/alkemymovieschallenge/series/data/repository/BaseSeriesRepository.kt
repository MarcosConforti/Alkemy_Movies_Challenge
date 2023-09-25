package com.example.alkemymovieschallenge.series.data.repository

import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.toDomainModel
import com.example.alkemymovieschallenge.series.data.api.APISeriesService
import com.example.alkemymovieschallenge.series.data.database.SeriesDao
import com.example.alkemymovieschallenge.series.data.database.SeriesEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

open class BaseSeriesRepository<T>(
    //dejamos declarada la variable api en caso de necesitar usarla
    private val api: APISeriesService,
    private val dao: SeriesDao
) {
    fun <R> getFromApiAndDatabase(
        apiCall: suspend () -> R,
        mapApiResponse: (R) -> List<T>,
        mapToEntity: (T) -> SeriesEntities,
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
        return dao.getAllSeries(). map {
                response ->
            response.map { it.toDomainModel() }
        }
    }

    suspend fun insertItems(items: List<SeriesEntities>) = dao.insertAll(items)

    suspend fun cleanList() = dao.deleteAllSeries()
}