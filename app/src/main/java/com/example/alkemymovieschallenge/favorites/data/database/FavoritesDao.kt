package com.example.alkemymovieschallenge.favorites.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites_table")
    fun getFavorites():Flow<List<FavoritesEntities>>

    @Query("SELECT EXISTS(SELECT * FROM favorites_table WHERE title = :title)")
    suspend fun checkFavorites(title: String):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(favorites: FavoritesEntities)

    @Query("DELETE FROM favorites_table WHERE title = :title")
    suspend fun deleteFromFavorites(title: String):Int
}