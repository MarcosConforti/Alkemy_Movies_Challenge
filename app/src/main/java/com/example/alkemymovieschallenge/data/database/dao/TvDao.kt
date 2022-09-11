package com.example.alkemymovieschallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alkemymovieschallenge.data.database.entities.TvEntities

@Dao
interface TvDao {

    @Query("SELECT * FROM series_table")
    suspend fun getAllSeries():List<TvEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(series:List<TvEntities>)

    //insertar una serie
    @Insert
    suspend fun insert(serie: TvEntities)

    @Query("DELETE FROM series_table")
    suspend fun deleteAllSeries()
}