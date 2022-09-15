package com.example.alkemymovieschallenge.domain.useCase.movies

import com.example.alkemymovieschallenge.data.MoviesRepository
import com.example.alkemymovieschallenge.domain.model.DomainModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPopularMoviesUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: MoviesRepository

    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        coEvery { repository.getPopularMoviesFromApi()} returns emptyList()
        //Then
        getPopularMoviesUseCase()
        //When
        coVerify(exactly = 1) { repository.getMoviesFromDataBase() }

    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(
            DomainModel(
                "Star Wars Episode IV: A New Hope", "9.5", "4-24-1977",
                "best movie", "image"
            )
        )
        coEvery { repository.getPopularMoviesFromApi()} returns myList
        //When
        val response = getPopularMoviesUseCase()
        //Then
        coVerify(exactly = 1) { repository.cleanList() }
        coVerify(exactly = 1) { repository.insertMovies(any()) }
        coVerify(exactly = 0) { repository.getMoviesFromDataBase() }
        assert(myList == response)
    }
}