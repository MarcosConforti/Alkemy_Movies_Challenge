package com.example.alkemymovieschallenge.movies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): Flow<List<MoviesEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies:List<MoviesEntities>)

    @Insert
    suspend fun insert(movie: MoviesEntities)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMovies()
}