package com.example.alkemymovieschallenge.series.data.repository

import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.toDomainModel
import com.example.alkemymovieschallenge.series.data.api.APISeriesService
import com.example.alkemymovieschallenge.series.data.api.model.SeriesModel
import com.example.alkemymovieschallenge.series.data.database.SeriesDao
import com.example.alkemymovieschallenge.series.data.database.toSeriesDataBase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las series, si de la api o db
class SeriesRepository @Inject constructor(
    private val api: APISeriesService,
    private val seriesDao: SeriesDao
):BaseSeriesRepository<SeriesModel>(api,seriesDao) {

    fun getPopularTvFromApi():Flow<NetworkState<List<DomainModel>>>{
        return getFromApiAndDatabase(
            apiCall = {api.getPopularTv()},
            mapApiResponse = { it.results },
            mapToEntity = { it.toSeriesDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }

    fun getTopRatedTvFromApi():Flow<NetworkState<List<DomainModel>>>{
        return getFromApiAndDatabase(
            apiCall = {api.getTopRatedTv()},
            mapApiResponse = { it.results },
            mapToEntity = { it.toSeriesDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }
    fun getAiringTodayTvFromApi():Flow<NetworkState<List<DomainModel>>>{
        return getFromApiAndDatabase(
            apiCall = {api.getAiringTodayTv()},
            mapApiResponse = { it.results },
            mapToEntity = { it.toSeriesDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }
    fun getOnTheAirTvFromApi():Flow<NetworkState<List<DomainModel>>>{
        return getFromApiAndDatabase(
            apiCall = {api.getOnTheAirTv()},
            mapApiResponse = { it.results },
            mapToEntity = { it.toSeriesDataBase() },
            mapToDomain = { it.toDomainModel() }
        )
    }
    /*fun getPopularTvFromApi(): Flow<NetworkState<List<DomainModel>>> =
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
                Log.e("SeriesRepository", "Error en getPopularTvFromApi: ${e.message}")
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
                Log.e("SeriesRepository", "Error en getTopRatedTvFromApi: ${e.message}")
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
                Log.e("SeriesRepository", "Error en getAiringTodayTvFromApi: ${e.message}")
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
                Log.e("SeriesRepository", "Error en getOnTheAirTvFromApi: ${e.message}")
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
*/
}