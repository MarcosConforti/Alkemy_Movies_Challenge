package com.example.alkemymovieschallenge.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.useCase.movies.GetAlternativeTitlesUseCase
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.model.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// in this ViewModel, we can get the similar titles of any selected movie
@HiltViewModel
class DetailViewModel @Inject constructor(private val alternativeTitlesUseCase: GetAlternativeTitlesUseCase) :
    ViewModel() {

    private val _alternativeStateFlow =
        MutableStateFlow<UIState<List<UIModel>>>(UIState.Loading)

    val alternativeState: StateFlow<UIState<List<UIModel>>> = _alternativeStateFlow

    fun getAlternativeTitles(id: String) {
        viewModelScope.launch {
            val alternativeTitles = alternativeTitlesUseCase.getAlternativeTitles(id)
            alternativeTitles.collect { alternativeTitle ->
                when (alternativeTitle) {
                    NetworkState.Loading -> {}
                    is NetworkState.Success -> {
                        val alternativeList = alternativeTitle.data.map { it.toUIModel() }
                        _alternativeStateFlow.value = UIState.Success(alternativeList)
                    }
                    is NetworkState.Error -> {
                        _alternativeStateFlow.value = UIState.Error(Error())
                    }
                }
            }
        }
    }
}