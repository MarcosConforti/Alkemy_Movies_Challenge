package com.example.alkemymovieschallenge.series.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {

    @Query("SELECT * FROM series_table")
    fun getAllSeries(): Flow<List<SeriesEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(series:List<SeriesEntities>)

    //insertar una serie
    @Insert
    suspend fun insert(serie: SeriesEntities)

    @Query("DELETE FROM series_table")
    suspend fun deleteAllSeries()
}