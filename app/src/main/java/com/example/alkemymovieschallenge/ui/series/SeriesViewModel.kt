package com.example.alkemymovieschallenge.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.list.TvList
import com.example.alkemymovieschallenge.domain.useCase.tv.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.collect
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

    private val _getSeriesLiveData = MutableStateFlow<NetworkState<TvList>>(NetworkState.Loading)

    val getSeriesLiveData: StateFlow<NetworkState<TvList>> = _getSeriesLiveData

    init {
        callSeriesUseCase()
    }

    private fun callSeriesUseCase() {
        viewModelScope.launch {
            val popularTvResult = getPopularTvUseCase()
            val onTheAirResult = getOnTheAirTvUseCase()
            val topRatedResult = getTopRatedTvUseCase()
            val airingTodayResult = getAiringTodayTvUseCase()

            combine(popularTvResult,onTheAirResult,
                topRatedResult,airingTodayResult){
                popular,onTheAir,
                topRated,airingToday ->
                if (popular is NetworkState.Success &&
                    onTheAir is NetworkState.Success &&
                    topRated is NetworkState.Success &&
                    airingToday is NetworkState.Success
                ) {
                    _getSeriesLiveData.value = NetworkState.Success(
                        TvList(
                            popularTv = popular.data,
                            onTheAir = onTheAir.data,
                            topRated = topRated.data,
                            airingToday = airingToday.data
                        )
                    )
                }else{
                    _getSeriesLiveData.value = NetworkState.Error(Error())
                }
            }.collect()
        }
    }
}