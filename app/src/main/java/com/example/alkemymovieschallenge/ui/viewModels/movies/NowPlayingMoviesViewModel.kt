package com.example.alkemymovieschallenge.ui.viewModels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.useCase.movies.GetNowPlayingMoviesUseCase
import com.example.alkemymovieschallenge.domain.model.DomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase) :
    ViewModel() {

    private val _getNowPlayingMoviesLiveData = MutableLiveData<List<DomainModel>>()

    val getNowPlayingMoviesLiveData: LiveData<List<DomainModel>> = _getNowPlayingMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            val result = getNowPlayingMoviesUseCase()
            if (result.isNotEmpty()) {
                _getNowPlayingMoviesLiveData.value = result
            }
        }
    }
}