package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.TvRepository
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOnTheAirTvUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: TvRepository

    lateinit var getOnTheAirTvUseCase: GetOnTheAirTvUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getOnTheAirTvUseCase = GetOnTheAirTvUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        coEvery { repository.getOnTheAirTvFromApi() } returns emptyList()
        //Then
        getOnTheAirTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getOnTheAirTvFromApi() }

    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(
            DomainTvModel(
                "Mandalorian Season 2", "9.5", "5-15-2020",
                "grogu", "image"
            )
        )
        coEvery { repository.getOnTheAirTvFromApi() } returns myList
        //When
        val response = getOnTheAirTvUseCase()
        //Then
        coVerify(exactly = 1) { repository.cleanList() }
        coVerify(exactly = 1) { repository.insertSeries(any()) }
        coVerify(exactly = 0) { repository.getSeriesFromDataBase() }
        assert(myList == response)
    }
}