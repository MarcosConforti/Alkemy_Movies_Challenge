package com.example.alkemymovieschallenge.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.ui.UIModel
import com.example.alkemymovieschallenge.core.ui.UIState
import com.example.alkemymovieschallenge.core.ui.toUIModel
import com.example.alkemymovieschallenge.detail.data.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    private val _detailState =
        MutableStateFlow<UIState<UIModel>>(UIState.Loading)

    val detailState: StateFlow<UIState<UIModel>> = _detailState


    fun getMovies(movieId: String) {
        viewModelScope.launch {
            _detailState.value = UIState.Loading
             repository.getMoviesDetail(movieId).collect { state ->
                when (state) {
                    NetworkState.Loading -> {}
                    is NetworkState.Success -> {
                        val movie = state.data.toUIModel()
                        _detailState.value = UIState.Success(
                            UIModel(
                                movie.id,
                                movie.title,
                                movie.voteAverage,
                                movie.releaseDate,
                                movie.overview,
                                movie.image
                            )
                        )
                    }
                    is NetworkState.Error -> {
                        UIState.Error(Error())
                    }
                }
            }
        }
    }
}