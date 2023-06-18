package com.example.alkemymovieschallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alkemymovieschallenge.data.database.entities.SeriesEntities

@Dao
interface SeriesDao {

    @Query("SELECT * FROM series_table")
    suspend fun getAllSeries():List<SeriesEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(series:List<SeriesEntities>)

    //insertar una serie
    @Insert
    suspend fun insert(serie: SeriesEntities)

    @Query("DELETE FROM series_table")
    suspend fun deleteAllSeries()
}