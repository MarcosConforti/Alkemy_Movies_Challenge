package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.repository.SeriesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.useCase.series.GetAiringTodayTvUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAiringTodayTvUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: SeriesRepository

    lateinit var getAiringTodayTvUseCase: GetAiringTodayTvUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAiringTodayTvUseCase = GetAiringTodayTvUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        coEvery { repository.getAiringTodayTvFromApi() } returns NetworkState.Success(emptyList())
        //Then
        getAiringTodayTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getAiringTodayTvFromApi() }

    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(
            DomainTvModel(
                0,"Mandalorian Season 2", "9.5", "5-15-2020",
                "grogu", "image"
            )
        )
        coEvery { repository.getAiringTodayTvFromApi() } returns NetworkState.Success(myList)
        //When
        val response = getAiringTodayTvUseCase()
        //Then
        coVerify(exactly = 1) { repository.cleanList() }
        coVerify(exactly = 1) { repository.insertSeries(any()) }
        coVerify(exactly = 0) { repository.getSeriesFromDataBase() }
        assert(myList == response)
    }
}
