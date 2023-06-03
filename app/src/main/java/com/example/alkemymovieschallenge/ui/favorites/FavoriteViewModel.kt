package com.example.alkemymovieschallenge.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainFavoritesModel
import com.example.alkemymovieschallenge.domain.useCase.favorites.GetFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.InsertFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.IsCheckedFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.RemoveToFavoriteUseCase
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.FavoritesUIModel
import com.example.alkemymovieschallenge.ui.model.toUIFavorites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val removeToFavoriteUseCase: RemoveToFavoriteUseCase,
    private val isCheckedFavoriteUseCase: IsCheckedFavoriteUseCase
) :
    ViewModel() {

    private val _favoritesUseCase =
        MutableStateFlow<UIState<List<FavoritesUIModel>>>(UIState.Loading)

    val favoriteLiveData: StateFlow<UIState<List<FavoritesUIModel>>> = _favoritesUseCase

    val verifyLiveData = MutableLiveData<Boolean>()

    fun getFavorites() {
        viewModelScope.launch {
            getFavoriteUseCase().collect { favorite ->
               when(favorite){
                   NetworkState.Loading -> {}
                   is NetworkState.Success -> {
                       val getFavorite = favorite.data.map { it.toUIFavorites() }
                       _favoritesUseCase.value = UIState.Success(getFavorite)
                   }
                   is NetworkState.Error ->{
                       _favoritesUseCase.value = UIState.Error(Error())
                   }
               }
            }
        }
    }

    fun addToFavorites(favorite: FavoritesUIModel) {
        viewModelScope.launch {
            insertFavoriteUseCase.addToFavorites(
                DomainFavoritesModel(
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