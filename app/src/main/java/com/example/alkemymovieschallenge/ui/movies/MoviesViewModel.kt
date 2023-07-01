package com.example.alkemymovieschallenge.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.useCase.movies.GetAlternativeTitlesUseCase
import com.example.alkemymovieschallenge.domain.useCase.movies.GetNowPlayingMoviesUseCase
import com.example.alkemymovieschallenge.domain.useCase.movies.GetPopularMoviesUseCase
import com.example.alkemymovieschallenge.domain.useCase.movies.GetTopRatedMoviesUseCase
import com.example.alkemymovieschallenge.domain.useCase.movies.GetUpComingMoviesUseCase
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.toUIModel
import com.example.alkemymovieschallenge.ui.model.uiList.MovieUIList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getAlternativeTitlesUseCase: GetAlternativeTitlesUseCase
) :
    ViewModel() {

    private val _getMoviesLiveData = MutableStateFlow<UIState<MovieUIList>>(UIState.Loading)

    val getMoviesLiveData: StateFlow<UIState<MovieUIList>> = _getMoviesLiveData
    init {
        callMoviesUseCase()
    }
    private fun callMoviesUseCase() {
        viewModelScope.launch {

            val popularResult = getPopularMoviesUseCase()
            val upComingResult = getUpComingMoviesUseCase()
            val topRatedResult = getTopRatedMoviesUseCase()
            val nowPlayingResult = getNowPlayingMoviesUseCase()

            combine(
                popularResult, topRatedResult,
                upComingResult, nowPlayingResult
            ) { popular, topRated, upComing, nowPlaying ->
                if (popular is NetworkState.Success &&
                    topRated is NetworkState.Success &&
                    upComing is NetworkState.Success &&
                    nowPlaying is NetworkState.Success
                ) {
                    _getMoviesLiveData.value = UIState.Success(
                        MovieUIList(
                            popular = popular.data.map { it.toUIModel() },
                            topRated = topRated.data.map { it.toUIModel() },
                            upComing = upComing.data.map { it.toUIModel() },
                            nowPlaying = nowPlaying.data.map { it.toUIModel() }
                        )
                    )
                } else {
                    _getMoviesLiveData.value = UIState.Error(Error())
                }
            }.collect()
        }
    }
}