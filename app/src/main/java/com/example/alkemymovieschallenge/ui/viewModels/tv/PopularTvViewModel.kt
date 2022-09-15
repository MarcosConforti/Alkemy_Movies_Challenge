package com.example.alkemymovieschallenge.ui.viewModels.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.useCase.tv.GetPopularTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularTvViewModel @Inject constructor(private val getPopularTvUseCase: GetPopularTvUseCase) :
    ViewModel() {

    private val _getPopularTvLiveData = MutableLiveData<List<DomainTvModel>>()

    val getPopularTvLiveData: LiveData<List<DomainTvModel>> = _getPopularTvLiveData

    init {
        callSeriesUseCase()
    }

    private fun callSeriesUseCase() {
        viewModelScope.launch {
            val result = getPopularTvUseCase()
            if (result.isNotEmpty()) {
                _getPopularTvLiveData.value = result
            }
        }
    }
}