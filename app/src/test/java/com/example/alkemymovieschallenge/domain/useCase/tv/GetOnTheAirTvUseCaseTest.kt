package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.repository.TvRepository
import com.example.alkemymovieschallenge.domain.NetworkState
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
        coEvery { repository.getOnTheAirTvFromApi() } returns NetworkState.Success(emptyList())
        //Then
        getOnTheAirTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getOnTheAirTvFromApi() }

    }

}