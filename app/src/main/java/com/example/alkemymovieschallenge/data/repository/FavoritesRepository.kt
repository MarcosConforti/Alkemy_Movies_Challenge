package com.example.alkemymovieschallenge.data.repository

import com.example.alkemymovieschallenge.data.database.dao.FavoritesDao
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainFavoritesModel
import com.example.alkemymovieschallenge.domain.model.toDomainFavoritesModel
import com.example.alkemymovieschallenge.domain.model.toFavoritesEntities
import com.example.alkemymovieschallenge.ui.model.toDomainFavorites
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class FavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao
) {

    suspend fun getFavorites(): Flow<NetworkState<List<DomainFavoritesModel>>> {
        return flow {
            val favorites = favoritesDao.getFavorites()
            if (favorites.isNotEmpty()) {
               emit( NetworkState.Success(favorites.map { it.toDomainFavoritesModel()}))
            } else {
                emit(NetworkState.Success(emptyList()))
            }
        }
    }

    suspend fun addToFavorites(favorites: DomainFavoritesModel) =
        favoritesDao.insertFavorites(favorites.toFavoritesEntities())

    suspend fun checkFavorite(id: String) = favoritesDao.checkFavorites(id)

    suspend fun cleanList(id: String) = favoritesDao.deleteFromFavorites(id)

}