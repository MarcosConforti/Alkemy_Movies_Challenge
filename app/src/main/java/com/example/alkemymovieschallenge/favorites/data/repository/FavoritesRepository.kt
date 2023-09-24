package com.example.alkemymovieschallenge.favorites.data.repository

import com.example.alkemymovieschallenge.favorites.data.database.FavoritesDao
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.core.domain.toDomainModel
import com.example.alkemymovieschallenge.core.domain.toFavoritesEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class FavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao
) {

    fun getFavorites(): Flow<NetworkState<List<DomainModel>>> =
        favoritesDao.getFavorites().map { favorites ->
            if (favorites.isNotEmpty()) {
                NetworkState.Success(favorites.map { it.toDomainModel() })
            } else {
                NetworkState.Success(emptyList())
            }
        }


    suspend fun addToFavorites(favorites: DomainModel) =
        favoritesDao.insertFavorites(favorites.toFavoritesEntities())

    suspend fun checkFavorite(title: String):Boolean = favoritesDao.checkFavorites(title)

    suspend fun cleanList(title: String) = favoritesDao.deleteFromFavorites(title)

}