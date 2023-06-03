package com.example.alkemymovieschallenge.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities
import com.example.alkemymovieschallenge.data.api.model.FavoritesModel
import com.example.alkemymovieschallenge.domain.model.DomainFavoritesModel
import com.example.alkemymovieschallenge.domain.useCase.favorites.InsertFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val favoriteUseCase: InsertFavoriteUseCase) :
    ViewModel() {

    private val _favoritesUseCase = MutableLiveData<List<FavoritesModel>>()

    val favoriteLiveData: LiveData<List<FavoritesModel>> = _favoritesUseCase


    fun addToFavorites(favorite: FavoritesEntities){
        viewModelScope.launch {
            favoriteUseCase.addToFavorites(
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

    suspend fun checkFavorite(id: String) = favoriteUseCase.checkFavorites(id)

    suspend fun removeFavorites(id: String) {
        viewModelScope.launch {
            favoriteUseCase.removeToFavorites(id)
        }
    }
}