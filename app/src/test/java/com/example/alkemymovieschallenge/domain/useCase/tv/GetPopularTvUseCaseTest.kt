package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.series.data.repository.SeriesRepository
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.series.domain.useCase.GetPopularTvUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPopularTvUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: SeriesRepository

    lateinit var getPopularTvUseCase: GetPopularTvUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularTvUseCase = GetPopularTvUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        val emptyListFlow: Flow<NetworkState<List<DomainModel>>> = flowOf(NetworkState.Success(emptyList()))
        coEvery { repository.getPopularTvFromApi() } returns emptyListFlow
        //Then
        getPopularTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getPopularTvFromApi() }

    }

}