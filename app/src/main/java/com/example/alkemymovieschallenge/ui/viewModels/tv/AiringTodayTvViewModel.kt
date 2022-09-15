package com.example.alkemymovieschallenge.ui.viewModels.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.useCase.tv.GetAiringTodayTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiringTodayTvViewModel @Inject constructor(private val getAiringTodayTvUseCase: GetAiringTodayTvUseCase) :
    ViewModel() {

    private val _getAiringTodayTvLiveData = MutableLiveData<List<DomainTvModel>>()

    val getAiringTodayTvLiveData: LiveData<List<DomainTvModel>> = _getAiringTodayTvLiveData

    init {
        callSeriesUseCase()
    }

    private fun callSeriesUseCase() {
        viewModelScope.launch {
            val result = getAiringTodayTvUseCase()
            if (result.isNotEmpty()) {
                _getAiringTodayTvLiveData.value = result
            }
        }
    }
}