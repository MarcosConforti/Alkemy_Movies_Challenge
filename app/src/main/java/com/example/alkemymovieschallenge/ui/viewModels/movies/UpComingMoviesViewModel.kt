package com.example.alkemymovieschallenge.ui.viewModels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.useCase.movies.GetUpComingMoviesUseCase
import com.example.alkemymovieschallenge.domain.model.DomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpComingMoviesViewModel @Inject constructor(private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase) :
    ViewModel() {

    private val _getUpComingMoviesLiveData = MutableLiveData<List<DomainModel>>()

    val getUpComingMoviesLiveData: LiveData<List<DomainModel>> = _getUpComingMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            val result = getUpComingMoviesUseCase()
            if (result.isNotEmpty()) {
                _getUpComingMoviesLiveData.value = result
            }
        }
    }
}