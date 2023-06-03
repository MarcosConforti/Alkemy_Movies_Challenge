package com.example.alkemymovieschallenge.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities
import com.example.alkemymovieschallenge.domain.useCase.favorites.GetFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.InsertFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.IsCheckedFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.RemoveToFavoriteUseCase
import com.example.alkemymovieschallenge.ui.model.FavoritesUIModel
import com.example.alkemymovieschallenge.ui.model.toUIFavorites
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val removeToFavoriteUseCase: RemoveToFavoriteUseCase,
    private val isCheckedFavoriteUseCase: IsCheckedFavoriteUseCase
) :
    ViewModel() {

    private val _favoritesUseCase = MutableLiveData<List<FavoritesUIModel>>()

    val favoriteLiveData: LiveData<List<FavoritesUIModel>> = _favoritesUseCase

    val verifyLiveData = MutableLiveData<Boolean>()

    fun getFavorites() {
        viewModelScope.launch {
            val getFavoriteUseCase = getFavoriteUseCase()
            if (getFavoriteUseCase.isNotEmpty()) {
                _favoritesUseCase.value = getFavoriteUseCase.map { it.toUIFavorites() }
            }
        }
    }

    fun addToFavorites(favorite: FavoritesUIModel) {
        viewModelScope.launch {
            insertFavoriteUseCase.addToFavorites(
                FavoritesEntities(
                    favorite.id,
                    favorite.title,
                    favorite.releaseDate,
                    favorite.voteAverage,
                    favorite.overview,
                    favorite.image
                )
            )
        }
    }

    suspend fun removeFavorites(id: String) {
        viewModelScope.launch {
            removeToFavoriteUseCase.removeToFavorites(id)
        }
    }
    private suspend fun isFavorite(id: String): Boolean =
        isCheckedFavoriteUseCase.checkFavorites(id)

    fun isChecked(title: String): Boolean {
        viewModelScope.launch {
            val isFavorite = isFavorite(title)
            verifyLiveData.postValue(isFavorite)
        }
        return verifyLiveData.value ?: false
    }
}