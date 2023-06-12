package com.example.alkemymovieschallenge.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.useCase.search.GetAllMoviesUseCase
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.MoviesUIModel
import com.example.alkemymovieschallenge.ui.model.toUIMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase) :
    ViewModel() {

    private val _getMoviesLiveData =
        MutableStateFlow<UIState<List<MoviesUIModel>>>(UIState.Loading)
    val getMoviesLiveData: StateFlow<UIState<List<MoviesUIModel>>> = _getMoviesLiveData

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            getAllMoviesUseCase().collect { allMoviesResult ->
                when (allMoviesResult) {
                    NetworkState.Loading -> TODO()
                    is NetworkState.Success -> {
                        val movieList = allMoviesResult.data.map { it.toUIMovie() }
                        _getMoviesLiveData.value =
                            UIState.Success(movieList)
                    }
                    is NetworkState.Error -> {
                        _getMoviesLiveData.value = UIState.Error(Error())
                    }
                }
            }
        }
    }
}