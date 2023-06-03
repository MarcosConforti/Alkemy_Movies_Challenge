package com.example.alkemymovieschallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites_table")
    suspend fun getFavorites():List<FavoritesEntities>

    @Query("SELECT id FROM favorites_table WHERE id = :id")
    suspend fun checkFavorites(id: String):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(favorites:FavoritesEntities)

    @Query("DELETE FROM favorites_table WHERE id = :id")
    suspend fun deleteFromFavorites(id: String):Int
}