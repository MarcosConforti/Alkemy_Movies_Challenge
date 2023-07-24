package com.example.alkemymovieschallenge.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.useCase.favorites.GetFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.InsertFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.IsCheckedFavoriteUseCase
import com.example.alkemymovieschallenge.domain.useCase.favorites.RemoveToFavoriteUseCase
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.model.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val removeToFavoriteUseCase: RemoveToFavoriteUseCase,
    private val isCheckedFavoriteUseCase: IsCheckedFavoriteUseCase
) :
    ViewModel() {

    private val _favoriteUIState =
        MutableStateFlow<UIState<List<UIModel>>>(UIState.Loading)

    val favoriteUIState: StateFlow<UIState<List<UIModel>>> = _favoriteUIState

    fun getFavorites() {
        viewModelScope.launch {
            getFavoriteUseCase().collect { favorite ->
                when (favorite) {
                    NetworkState.Loading -> TODO()
                    is NetworkState.Success -> {
                        val getFavorite = favorite.data.map { it.toUIModel() }
                        _favoriteUIState.value = UIState.Success(getFavorite)
                    }

                    is NetworkState.Error -> {
                        _favoriteUIState.value = UIState.Error(Error())
                    }
                }
            }
        }
    }


    fun addToFavorites(favorite: UIModel) {
        viewModelScope.launch {
            val id = UUID.randomUUID().hashCode() // generamos un nuevo identificador único
            val insert = DomainModel(
                id,
                favorite.title,
                favorite.releaseDate,
                favorite.voteAverage,
                favorite.overview,
                favorite.image
            )
            insertFavoriteUseCase.addToFavorites(insert)
        }
    }

    fun removeFavorites(title: String) {
        viewModelScope.launch {
            removeToFavoriteUseCase.removeToFavorites(title)
        }
    }

    suspend fun isChecked(title: String): Boolean =
        isCheckedFavoriteUseCase.checkFavorites(title)
}
