package com.example.alkemymovieschallenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.MovieList
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.useCase.movies.GetNowPlayingMoviesUseCase
import com.example.alkemymovieschallenge.domain.useCase.movies.GetPopularMoviesUseCase
import com.example.alkemymovieschallenge.domain.useCase.movies.GetTopRatedMoviesUseCase
import com.example.alkemymovieschallenge.domain.useCase.movies.GetUpComingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,

    ) :
    ViewModel() {

    private val _getMoviesLiveData = MutableLiveData<NetworkState<MovieList>>()

    val getMoviesLiveData: LiveData<NetworkState<MovieList>> = _getMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {

            val popularResult = getPopularMoviesUseCase()
            val upComingResult = getUpComingMoviesUseCase()
            val topRatedResult = getTopRatedMoviesUseCase()
            val nowPlayingResult = getNowPlayingMoviesUseCase()
            if (popularResult is NetworkState.Success && upComingResult is NetworkState.Success
                && topRatedResult is NetworkState.Success &&
                nowPlayingResult is NetworkState.Success
            ) {
                _getMoviesLiveData.value = NetworkState.Success(MovieList(
                    popular = popularResult.data,
                    upComing = upComingResult.data,
                    topRated = topRatedResult.data,
                    nowPlaying = nowPlayingResult.data
                ))
            }else{
                _getMoviesLiveData.value = NetworkState.Error(Error())
            }

        }
    }
}