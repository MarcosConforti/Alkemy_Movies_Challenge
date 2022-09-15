package com.example.alkemymovieschallenge.ui.viewModels.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.useCase.favorites.InsertFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val favoriteUseCase: InsertFavoriteUseCase) :
    ViewModel() {

    private val _insetMovieUseCase = MutableLiveData<List<DomainModel>>()

    val insertMovieUseCase: LiveData<List<DomainModel>> = _insetMovieUseCase


    fun insertMovie(movie: MoviesEntities) {
        viewModelScope.launch {
            favoriteUseCase.insertMovie(movie)
        }
    }
}