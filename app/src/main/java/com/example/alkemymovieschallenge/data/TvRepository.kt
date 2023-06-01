package com.example.alkemymovieschallenge.data

import com.example.alkemymovieschallenge.data.database.dao.TvDao
import com.example.alkemymovieschallenge.data.database.entities.TvEntities
import com.example.alkemymovieschallenge.data.database.entities.toTvDataBase
import com.example.alkemymovieschallenge.data.network.APIService
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.model.toDomainTv
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las series, si de la api o db
class TvRepository @Inject constructor(
    private val api: APIService,
    private val tvDao:TvDao
) {
    suspend fun getPopularTvFromApi(): NetworkState<List<DomainTvModel>> =
        try {
            var seriesApi = api.getPopularTv().results
            if (seriesApi.isNotEmpty()) {
                cleanList()
                insertSeries(seriesApi.map { it.toTvDataBase() })
                NetworkState.Success(seriesApi.map { it.toDomainTv() })

            } else {
                //si esta vacio, que recupere los datos de la db
                val seriesDb = getSeriesFromDataBase()
                NetworkState.Success(seriesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }


    suspend fun getTopRatedTvFromApi(): NetworkState<List<DomainTvModel>> =
        try {
            var seriesApi = api.getTopRatedTv().results
            if (seriesApi.isNotEmpty()) {
                cleanList()
                insertSeries(seriesApi.map { it.toTvDataBase() })
                NetworkState.Success(seriesApi.map { it.toDomainTv() })

            } else {
                val seriesDb = getSeriesFromDataBase()
                NetworkState.Success(seriesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }

    suspend fun getAiringTodayTvFromApi(): NetworkState<List<DomainTvModel>> =
        try {
            var seriesApi = api.getAiringTodayTv().results
            if (seriesApi.isNotEmpty()) {
                cleanList()
                insertSeries(seriesApi.map { it.toTvDataBase() })
                NetworkState.Success(seriesApi.map { it.toDomainTv() })

            } else {
                val seriesDb = getSeriesFromDataBase()
                NetworkState.Success(seriesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }
    suspend fun getOnTheAirTvFromApi(): NetworkState<List<DomainTvModel>> =
        try {
            var seriesApi = api.getOnTheAirTv().results
            if (seriesApi.isNotEmpty()) {
                cleanList()
                insertSeries(seriesApi.map { it.toTvDataBase() })
                NetworkState.Success(seriesApi.map { it.toDomainTv() })

            } else {
                val seriesDb = getSeriesFromDataBase()
                NetworkState.Success(seriesDb)
            }

        } catch (e: Throwable) {
            NetworkState.Error(e)
        }
    suspend fun getSeriesFromDataBase(): List<DomainTvModel> {
        val response = tvDao.getAllSeries()
        return response.map { it.toDomainTv() }
    }

    suspend fun insertSeries(series: List<TvEntities>) {
        tvDao.insertAll(series)
    }

    suspend fun insertOnlySerie(serie: TvEntities) {
        tvDao.insert(serie)
    }

    suspend fun cleanList() {
        tvDao.deleteAllSeries()
    }



}