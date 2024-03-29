package com.example.alkemymovieschallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): Flow<List<MoviesEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies:List<MoviesEntities>)

    //insertar una pelicula
    @Insert
    suspend fun insert(movie: MoviesEntities)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMovies()
}