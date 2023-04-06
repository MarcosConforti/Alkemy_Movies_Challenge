package com.example.alkemymovieschallenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.list.TvList
import com.example.alkemymovieschallenge.domain.useCase.tv.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getPopularTvUseCase: GetPopularTvUseCase,
    private val getOnTheAirTvUseCase: GetOnTheAirTvUseCase,
    private val getTopRatedTvUseCase: GetTopRatedTvUseCase,
    private val getAiringTodayTvUseCase: GetAiringTodayTvUseCase
):
 ViewModel() {

    private val _getSeriesLiveData = MutableLiveData<NetworkState<TvList>>()

    val getSeriesLiveData: LiveData<NetworkState<TvList>> = _getSeriesLiveData

    init {
        callSeriesUseCase()
    }

    private fun callSeriesUseCase() {
        viewModelScope.launch {
            val popularTvResult = getPopularTvUseCase()
            val onTheAirResult = getOnTheAirTvUseCase()
            val topRatedResult = getTopRatedTvUseCase()
            val airingTodayResult = getAiringTodayTvUseCase()
            if (popularTvResult is NetworkState.Success && onTheAirResult is NetworkState.Success
                && topRatedResult is NetworkState.Success &&
                airingTodayResult is NetworkState.Success
            ) {
                _getSeriesLiveData.value = NetworkState.Success(
                    TvList(
                    popularTv = popularTvResult.data,
                    onTheAir = onTheAirResult.data,
                    topRated = topRatedResult.data,
                    airingToday = airingTodayResult.data
                )
                )
            }else{
                _getSeriesLiveData.value = NetworkState.Error(Error())
            }

        }
    }
}