package com.example.alkemymovieschallenge.domain.useCase.movies

import com.example.alkemymovieschallenge.data.MoviesRepository
import com.example.alkemymovieschallenge.domain.model.DomainModel
import javax.inject.Inject

class GetLatestMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): List<DomainModel> = moviesRepository.getLatestMoviesFromApi()
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
