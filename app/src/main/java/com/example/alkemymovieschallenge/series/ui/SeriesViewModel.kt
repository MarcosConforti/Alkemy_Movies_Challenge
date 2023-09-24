package com.example.alkemymovieschallenge.series.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.series.domain.useCase.GetAiringTodayTvUseCase
import com.example.alkemymovieschallenge.series.domain.useCase.GetOnTheAirTvUseCase
import com.example.alkemymovieschallenge.series.domain.useCase.GetPopularTvUseCase
import com.example.alkemymovieschallenge.series.domain.useCase.GetTopRatedTvUseCase
import com.example.alkemymovieschallenge.core.ui.UIState
import com.example.alkemymovieschallenge.core.ui.toUIModel
import com.example.alkemymovieschallenge.series.ui.model.SeriesUIList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getPopularTvUseCase: GetPopularTvUseCase,
    private val getOnTheAirTvUseCase: GetOnTheAirTvUseCase,
    private val getTopRatedTvUseCase: GetTopRatedTvUseCase,
    private val getAiringTodayTvUseCase: GetAiringTodayTvUseCase,
):
 ViewModel() {

    private val _getSeriesUIState =
        MutableStateFlow<UIState<SeriesUIList>>(UIState.Loading)

    val getSeriesUIState: StateFlow<UIState<SeriesUIList>> = _getSeriesUIState

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
                    _getSeriesUIState.value = UIState.Success(
                        SeriesUIList(
                            popularTv = popular.data.map { it.toUIModel() },
                            onTheAir = onTheAir.data.map { it.toUIModel() },
                            topRated = topRated.data.map { it.toUIModel() },
                            airingToday = airingToday.data.map { it.toUIModel() }
                        )
                    )
                }else{
                    _getSeriesUIState.value = UIState.Error(Error())
                }
            }.collect()
        }
    }
}