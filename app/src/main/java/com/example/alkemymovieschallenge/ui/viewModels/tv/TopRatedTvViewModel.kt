package com.example.alkemymovieschallenge.ui.viewModels.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.domain.useCase.tv.GetTopRatedTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedTvViewModel @Inject constructor(private val getTopRatedTvUseCase: GetTopRatedTvUseCase) :
    ViewModel() {

    private val _getTopRatedTvLiveData = MutableLiveData<List<DomainTvModel>>()

    val getTopRatedTvLiveData: LiveData<List<DomainTvModel>> = _getTopRatedTvLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            val result = getTopRatedTvUseCase()
            if (result.isNotEmpty()) {
                _getTopRatedTvLiveData.value = result
            }
        }
    }
}