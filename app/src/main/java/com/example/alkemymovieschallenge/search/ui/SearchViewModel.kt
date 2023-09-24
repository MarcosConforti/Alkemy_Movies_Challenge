package com.example.alkemymovieschallenge.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.search.domain.useCase.GetAllMoviesUseCase
import com.example.alkemymovieschallenge.core.ui.UIState
import com.example.alkemymovieschallenge.core.ui.UIModel
import com.example.alkemymovieschallenge.core.ui.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase
    ) :
    ViewModel() {

    private val _getAllMoviesUIState =
        MutableStateFlow<UIState<List<UIModel>>>(UIState.Loading)
    val getAllMoviesState: StateFlow<UIState<List<UIModel>>> = _getAllMoviesUIState

    init {
        callMoviesUseCase()
    }

    private fun callMoviesUseCase() {
        viewModelScope.launch {
            getAllMoviesUseCase().collect { allMoviesResult ->
                when (allMoviesResult) {
                    NetworkState.Loading-> TODO()
                    is NetworkState.Success -> {
                        val movieList = allMoviesResult.data.map { it.toUIModel() }
                        _getAllMoviesUIState.value =
                            UIState.Success(movieList)
                    }
                    is NetworkState.Error -> {
                        _getAllMoviesUIState.value = UIState.Error(Error())
                    }
                }
            }
        }
    }
}

