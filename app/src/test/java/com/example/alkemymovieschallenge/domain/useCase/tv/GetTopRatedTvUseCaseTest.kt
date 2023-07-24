package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.repository.SeriesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.useCase.series.GetTopRatedTvUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTopRatedTvUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: SeriesRepository

    lateinit var getTopRatedTvUseCase: GetTopRatedTvUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTopRatedTvUseCase = GetTopRatedTvUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        val emptyListFlow: Flow<NetworkState<List<DomainModel>>> = flowOf(NetworkState.Success(emptyList()))
        coEvery { repository.getTopRatedTvFromApi() } returns emptyListFlow
        //Then
        getTopRatedTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getTopRatedTvFromApi() }

    }

}