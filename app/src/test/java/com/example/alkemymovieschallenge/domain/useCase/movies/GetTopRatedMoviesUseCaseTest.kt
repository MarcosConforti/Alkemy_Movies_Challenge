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

class GetTopRatedMoviesUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: MoviesRepository

    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        coEvery { repository.getTopRatedMoviesFromApi()} returns emptyList()
        //Then
        getTopRatedMoviesUseCase()
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
        coEvery { repository.getTopRatedMoviesFromApi()} returns myList
        //When
        val response = getTopRatedMoviesUseCase()
        //Then
        coVerify(exactly = 1) { repository.cleanList() }
        coVerify(exactly = 1) { repository.insertMovies(any()) }
        coVerify(exactly = 0) { repository.getMoviesFromDataBase() }
        assert(myList == response)
    }
}