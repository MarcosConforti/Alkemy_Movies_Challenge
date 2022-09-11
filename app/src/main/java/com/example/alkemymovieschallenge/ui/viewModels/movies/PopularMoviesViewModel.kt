package com.example.alkemymovieschallenge.ui.viewModels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.useCase.movies.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    ViewModel() {

    private val _getMoviesLiveData = MutableLiveData<List<DomainModel>>()

    val getMoviesLiveData: LiveData<List<DomainModel>> = _getMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            val result = getPopularMoviesUseCase()
            if (result.isNotEmpty()) {
                _getMoviesLiveData.value = result
            }
        }
    }
}