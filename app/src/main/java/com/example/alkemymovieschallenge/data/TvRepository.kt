package com.example.alkemymovieschallenge.data

import com.example.alkemymovieschallenge.data.database.dao.TvDao
import com.example.alkemymovieschallenge.data.database.entities.TvEntities
import com.example.alkemymovieschallenge.data.model.TvModel
import com.example.alkemymovieschallenge.data.network.TvService
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.model.toDomainTv
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las series, si de la api o db
class TvRepository @Inject constructor(
    private val api: TvService,
    private val tvDao:TvDao
) {
    suspend fun getPopularTvFromApi(): List<DomainTvModel> {
        //recupero las peliculas
        val response: List<TvModel> = api.getPopularTv()
        //mapeo
        return response.map { it.toDomainTv() }
    }

    suspend fun getLatestTvFromApi(): List<DomainTvModel> {

        val response: List<TvModel> = api.getLatestTv()

        return response.map { it.toDomainTv() }
    }

    suspend fun getTopRatedTvFromApi(): List<DomainTvModel> {

        val response: List<TvModel> = api.getTopRatedTV()

        return response.map { it.toDomainTv() }
    }
    suspend fun getAiringTodayTvFromApi():List<DomainTvModel>{
        val response: List<TvModel> = api.getAiringTodayTv()
        return response.map { it.toDomainTv() }
    }
    suspend fun getOnTheAirTvFromApi():List<DomainTvModel>{
        val response: List<TvModel> = api.getOnTheAirTv()
        return response.map { it.toDomainTv() }
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