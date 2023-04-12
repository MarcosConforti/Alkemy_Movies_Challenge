package com.example.alkemymovieschallenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.useCase.search.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getAllMoviesUseCase: GetAllMoviesUseCase) :
    ViewModel() {

    private val _getMoviesLiveData = MutableLiveData<NetworkState<List<DomainModel>?>>()
    val getMoviesLiveData: LiveData<NetworkState<List<DomainModel>?>> = _getMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {

            val allMoviesResult = getAllMoviesUseCase()

            if (allMoviesResult is NetworkState.Success) {
                _getMoviesLiveData.value = NetworkState.Success(allMoviesResult.data)
            } else {
                _getMoviesLiveData.value = NetworkState.Error(Error())
            }
        }
    }
}