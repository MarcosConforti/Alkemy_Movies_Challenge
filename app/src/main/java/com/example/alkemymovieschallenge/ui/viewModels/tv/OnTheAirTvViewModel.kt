package com.example.alkemymovieschallenge.ui.viewModels.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.useCase.tv.GetOnTheAirTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnTheAirTvViewModel @Inject constructor(private val getOnTheAirTvUseCase: GetOnTheAirTvUseCase) :
    ViewModel() {

    private val _getOnTheAirTvLiveData = MutableLiveData<List<DomainTvModel>>()

    val getOnTheAirTvLiveData: LiveData<List<DomainTvModel>> = _getOnTheAirTvLiveData

    init {
        callSeriesUseCase()
    }

    private fun callSeriesUseCase() {
        viewModelScope.launch {
            val result = getOnTheAirTvUseCase()
            if (result.isNotEmpty()) {
                _getOnTheAirTvLiveData.value = result
            }
        }
    }
}