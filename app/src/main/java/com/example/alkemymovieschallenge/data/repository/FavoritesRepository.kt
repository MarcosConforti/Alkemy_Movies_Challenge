package com.example.alkemymovieschallenge.data.repository

import com.example.alkemymovieschallenge.data.database.dao.FavoritesDao
import com.example.alkemymovieschallenge.domain.model.DomainFavoritesModel
import com.example.alkemymovieschallenge.domain.model.toFavoritesEntities
import com.example.alkemymovieschallenge.ui.model.toDomainFavorites
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class FavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao
) {

    suspend fun getFavorites(): List<DomainFavoritesModel> {
        val favorites = favoritesDao.getFavorites()
       return if (favorites.isNotEmpty()) {
            favorites.map { it.toDomainFavorites() }
        } else {
            emptyList()
        }
    }

    suspend fun addToFavorites(favorites: DomainFavoritesModel) =
        favoritesDao.insertFavorites(favorites.toFavoritesEntities())

    suspend fun checkFavorite(id: String) = favoritesDao.checkFavorites(id)

    suspend fun cleanList(id: String) = favoritesDao.deleteFromFavorites(id)

}