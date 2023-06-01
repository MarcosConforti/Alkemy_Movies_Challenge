package com.example.alkemymovieschallenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.useCase.search.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getAllMoviesUseCase: GetAllMoviesUseCase) :
    ViewModel() {

    private val _getMoviesLiveData =
        MutableStateFlow<NetworkState<List<DomainModel>>>(NetworkState.Loading)
    val getMoviesLiveData: StateFlow<NetworkState<List<DomainModel>>> = _getMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            getAllMoviesUseCase().collect { allMoviesResult ->
                when (allMoviesResult) {
                    NetworkState.Loading -> TODO()
                    is NetworkState.Success -> {
                        val movieList = allMoviesResult.data
                        _getMoviesLiveData.value = NetworkState.Success(movieList)
                    }

                    is NetworkState.Error -> {
                        _getMoviesLiveData.value = NetworkState.Error(Error())
                    }
                }
            }
        }
    }
}