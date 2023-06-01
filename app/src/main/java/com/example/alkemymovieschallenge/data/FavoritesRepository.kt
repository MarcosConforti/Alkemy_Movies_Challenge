package com.example.alkemymovieschallenge.data

import com.example.alkemymovieschallenge.data.database.dao.FavoritesDao
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities
import com.example.alkemymovieschallenge.data.model.FavoritesModel
import javax.inject.Inject

//esta clase funciona para seleccionar de donde el programa tomara las peliculas, si de la api o db
class FavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao
) {

    suspend fun addToFavorites(favorites: FavoritesEntities) {
        val favorites =favoritesDao.insert(favorites)
    }

    suspend fun checkFavorite(id: String) = favoritesDao.checkFavorites(id)

    suspend fun cleanList(id: String) = favoritesDao.deleteFromFavorites(id)

}