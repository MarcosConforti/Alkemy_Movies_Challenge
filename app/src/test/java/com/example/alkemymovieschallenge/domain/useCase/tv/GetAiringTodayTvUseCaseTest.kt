package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.series.data.repository.SeriesRepository
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.series.domain.useCase.GetAiringTodayTvUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
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
        val emptyListFlow: Flow<NetworkState<List<DomainModel>>> = flowOf(NetworkState.Success(emptyList()))
        coEvery { repository.getAiringTodayTvFromApi() } returns emptyListFlow
        //Then
        getAiringTodayTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getAiringTodayTvFromApi() }

    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(
            DomainModel(
                0,"Mandalorian Season 2", "9.5", "5-15-2020",
                "grogu", "image"
            )
        )
        val myListFlow = flow { emit(NetworkState.Success(myList)) }
        coEvery { repository.getAiringTodayTvFromApi() } returns myListFlow

        //When
        val response = getAiringTodayTvUseCase()
        //Then
        coVerify(exactly = 1) { repository.cleanList() }
        coVerify(exactly = 1) { repository.insertSeries(any()) }
        coVerify(exactly = 0) { repository.getSeriesFromDataBase() }
        assert(myList == response)
    }
}
