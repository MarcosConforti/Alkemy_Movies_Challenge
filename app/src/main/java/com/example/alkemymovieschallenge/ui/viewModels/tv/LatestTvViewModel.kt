package com.example.alkemymovieschallenge.ui.viewModels.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.useCase.tv.GetLatestTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestTvViewModel @Inject constructor(private val getLatestTvUseCase: GetLatestTvUseCase) :
    ViewModel() {

    private val _getLatestTvLiveData = MutableLiveData<List<DomainTvModel>>()

    val getLatestTvLiveData: LiveData<List<DomainTvModel>> = _getLatestTvLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            val result = getLatestTvUseCase()
            if (result.isNotEmpty()) {
                _getLatestTvLiveData.value = result
            }
        }
    }
}