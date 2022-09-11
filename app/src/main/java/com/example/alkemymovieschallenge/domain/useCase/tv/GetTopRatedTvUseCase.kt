package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.TvRepository
import com.example.alkemymovieschallenge.data.database.entities.toTvDataBase
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import javax.inject.Inject

class GetTopRatedTvUseCase @Inject constructor(private val tvRepository: TvRepository) {

    suspend operator fun invoke(): List<DomainTvModel> { //tvRepository.getTopRatedTvFromApi()
        val series = tvRepository.getTopRatedTvFromApi()

        return if (series.isNotEmpty()) {
            tvRepository.cleanList()
            tvRepository.insertSeries(series.map { it.toTvDataBase() })
            series
        } else {
            //si esta vacio, que recupere los datos de la db
            tvRepository.getSeriesFromDataBase()
        }
    }

}
