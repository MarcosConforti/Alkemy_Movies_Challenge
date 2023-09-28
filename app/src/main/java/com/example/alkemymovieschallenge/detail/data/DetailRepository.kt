package com.example.alkemymovieschallenge.detail.data

import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.NetworkState.Error
import com.example.alkemymovieschallenge.core.domain.NetworkState.Success
import com.example.alkemymovieschallenge.core.domain.toDomainModel
import com.example.alkemymovieschallenge.detail.data.api.APIDetailService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiDetailService: APIDetailService) {

    suspend fun getMoviesDetail(movieId:String): Flow<NetworkState<DomainModel>> {
        return flow {
            try {
                val response = apiDetailService.getMovieDetail(movieId)
                kotlin.runCatching { response }
                    .onSuccess { emit(Success(response.toDomainModel())) }
                    .onFailure { Error("La respuesta esta vacia") }
            }catch (e:Throwable){
                emit(Error(e))
            }
        }
    }
    suspend fun getSeriesDetail(seriesId:String): Flow<NetworkState<DomainModel>> {
        return flow {
            try {
                val response = apiDetailService.getSeriesDetail(seriesId)
                kotlin.runCatching { response }
                    .onSuccess { emit(Success(response.toDomainModel())) }
                    .onFailure { Error("La respuesta esta vacia") }
            }catch (e:Throwable){
                emit(Error(e))
            }
        }
    }
}