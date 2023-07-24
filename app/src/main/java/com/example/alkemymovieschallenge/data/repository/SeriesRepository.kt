package com.example.alkemymovieschallenge.data.repository

import com.example.alkemymovieschallenge.data.api.APIService
import com.example.alkemymovieschallenge.data.database.dao.SeriesDao
import com.example.alkemymovieschallenge.data.database.entities.SeriesEntities
import com.example.alkemymovieschallenge.data.database.entities.toSeriesDataBase
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las series, si de la api o db
class SeriesRepository @Inject constructor(
    private val api: APIService,
    private val seriesDao: SeriesDao
) {
    fun getPopularTvFromApi(): Flow<NetworkState<List<DomainModel>>> =
        flow {
            try {
                var seriesApi = api.getPopularTv().results
                if (seriesApi.isNotEmpty()) {
                    cleanList()
                    insertSeries(seriesApi.map { it.toSeriesDataBase() })
                    emit(NetworkState.Success(seriesApi.map { it.toDomainModel() }))
                } else {
                    //si esta vacio, que recupere los datos de la db
                    val seriesDb = getSeriesFromDataBase()
                    seriesDb.collect{dbSeries->
                        emit(NetworkState.Success(dbSeries))
                    }
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }

    fun getTopRatedTvFromApi(): Flow<NetworkState<List<DomainModel>>> =
        flow {
            try {
                var seriesApi = api.getTopRatedTv().results
                if (seriesApi.isNotEmpty()) {
                    cleanList()
                    insertSeries(seriesApi.map { it.toSeriesDataBase() })
                    emit(NetworkState.Success(seriesApi.map { it.toDomainModel() }))
                } else {
                    val seriesDb = getSeriesFromDataBase()
                    seriesDb.collect{dbSeries->
                        emit(NetworkState.Success(dbSeries))
                    }
                }
            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }

     fun getAiringTodayTvFromApi(): Flow<NetworkState<List<DomainModel>>> =
        flow {
            try {
                var seriesApi = api.getAiringTodayTv().results
                if (seriesApi.isNotEmpty()) {
                    cleanList()
                    insertSeries(seriesApi.map { it.toSeriesDataBase() })
                    emit(NetworkState.Success(seriesApi.map { it.toDomainModel() }))

                } else {
                    val seriesDb = getSeriesFromDataBase()
                    seriesDb.collect{dbSeries->
                        emit(NetworkState.Success(dbSeries))
                    }
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }

    fun getOnTheAirTvFromApi(): Flow<NetworkState<List<DomainModel>>> =
        flow {
            try {
                var seriesApi = api.getOnTheAirTv().results
                if (seriesApi.isNotEmpty()) {
                    cleanList()
                    insertSeries(seriesApi.map { it.toSeriesDataBase() })
                    emit(NetworkState.Success(seriesApi.map { it.toDomainModel() }))

                } else {
                    val seriesDb = getSeriesFromDataBase()
                    emitAll(seriesDb.map { dbSeries ->
                        NetworkState.Success(dbSeries)
                    })
                }

            } catch (e: Throwable) {
                emit(NetworkState.Error(e))
            }
        }

    fun getSeriesFromDataBase(): Flow<List<DomainModel>> =
        flow{
            seriesDao.getAllSeries().map { response->
                response.map { it.toDomainModel() }
            }
        }

    suspend fun insertSeries(series: List<SeriesEntities>) =
        seriesDao.insertAll(series)


    suspend fun cleanList() =
        seriesDao.deleteAllSeries()

}