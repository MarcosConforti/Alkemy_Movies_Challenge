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
    fun `when the api doesn't return anything then get values from database`() = runBlocking {

        //Given
        val emptyListFlow: Flow<NetworkState<List<DomainModel>>> = flowOf(NetworkState.Success(emptyList()))
        coEvery { repository.getAiringTodayTvFromApi() } returns emptyListFlow
        //Then
        getAiringTodayTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getFromDatabase() }

    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(
            DomainModel(
                "1","Mandalorian Season 2", "9.5", "5-15-2020",
                "grogu", "image"
            )
        )
        val myListFlow = flow { emit(NetworkState.Success(myList)) }
        coEvery { repository.getAiringTodayTvFromApi() } returns myListFlow

        //When
        val response = getAiringTodayTvUseCase()
        //Then
        coVerify(exactly = 1) { repository.getAiringTodayTvFromApi()}
        assert(response == myListFlow)
    }

    @Test
    fun `when the api returns an error`() {
        runBlocking {
            // Given
            val error: Flow<NetworkState<List<DomainModel>>> = flowOf( NetworkState.Error(Error()))
            coEvery { repository.getAiringTodayTvFromApi() } returns error

            // When
            val response = getAiringTodayTvUseCase()

            // Then
            coVerify { repository.getAiringTodayTvFromApi() }
            coVerify(exactly = 0) { repository.cleanList() }
            coVerify(exactly = 0) { repository.insertItems(any()) }
            coVerify(exactly = 0) { repository.getFromDatabase() }
            assert(error == response)
        }
    }
}
