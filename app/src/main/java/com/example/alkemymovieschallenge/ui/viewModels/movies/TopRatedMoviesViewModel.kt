package com.example.alkemymovieschallenge.ui.viewModels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.useCase.movies.GetTopRatedMoviesUseCase
import com.example.alkemymovieschallenge.domain.model.DomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase) :
    ViewModel() {

    private val _getTopRatedMoviesLiveData = MutableLiveData<List<DomainModel>>()

    val getTopRatedMoviesLiveData: LiveData<List<DomainModel>> = _getTopRatedMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            val result = getTopRatedMoviesUseCase()
            if (result.isNotEmpty()) {
                _getTopRatedMoviesLiveData.value = result
            }
        }
    }
}