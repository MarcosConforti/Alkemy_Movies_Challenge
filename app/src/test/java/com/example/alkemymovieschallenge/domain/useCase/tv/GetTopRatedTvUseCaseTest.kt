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

class GetTopRatedTvUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: TvRepository

    lateinit var getTopRatedTvUseCase: GetTopRatedTvUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTopRatedTvUseCase = GetTopRatedTvUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given
        coEvery { repository.getTopRatedTvFromApi() } returns emptyList()
        //Then
        getTopRatedTvUseCase()
        //When
        coVerify(exactly = 1) { repository.getTopRatedTvFromApi() }

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
        coEvery { repository.getTopRatedTvFromApi() } returns myList
        //When
        val response = getTopRatedTvUseCase()
        //Then
        coVerify(exactly = 1) { repository.cleanList() }
        coVerify(exactly = 1) { repository.insertSeries(any()) }
        coVerify(exactly = 0) { repository.getSeriesFromDataBase() }
        assert(myList == response)
    }
}