package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.TvRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPopularTvUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: TvRepository

    lateinit var getPopularTvUseCase: GetPopularTvUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularTvUseCase = GetPopularTvUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        coEvery { repository.getPopularTvFromApi() } returns NetworkState.Success(emptyList())
        //Then
        getPopularTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getPopularTvFromApi() }

    }

}