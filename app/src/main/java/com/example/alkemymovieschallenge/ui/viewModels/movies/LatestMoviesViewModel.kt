package com.example.alkemymovieschallenge.ui.viewModels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.useCase.movies.GetLatestMoviesUseCase
import com.example.alkemymovieschallenge.domain.model.DomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestMoviesViewModel @Inject constructor(private val getLatestMoviesUseCase: GetLatestMoviesUseCase) :
    ViewModel() {

    private val _getLatestMoviesLiveData = MutableLiveData<List<DomainModel>>()

    val getLatestMoviesLiveData: LiveData<List<DomainModel>> = _getLatestMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            val result = getLatestMoviesUseCase()
            if (result.isNotEmpty()) {
                _getLatestMoviesLiveData.value = result
            }
        }
    }
}