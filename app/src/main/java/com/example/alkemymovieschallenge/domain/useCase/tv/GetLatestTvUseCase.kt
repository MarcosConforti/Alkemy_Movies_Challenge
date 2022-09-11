package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.TvRepository
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import javax.inject.Inject

class GetLatestTvUseCase @Inject constructor(private val tvRepository: TvRepository) {

    suspend operator fun invoke(): List<DomainTvModel> = tvRepository.getLatestTvFromApi()
        //val movies = moviesRepository.getLatestFromApi()
        /*return if (movies.isNotEmpty()) {
            moviesRepository.cleanList()
            moviesRepository.insertMovies(movies.map { it.toDataBase() })
            movies
        } else {
            //si esta vacio, que recupere los datos de la db
            moviesRepository.getMoviesFromDataBase()
        }
    }*/

}
