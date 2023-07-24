package com.example.alkemymovieschallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alkemymovieschallenge.data.database.dao.SeriesDao
import com.example.alkemymovieschallenge.data.database.entities.SeriesEntities

@Database (entities = [SeriesEntities::class], version = 1)
abstract class SeriesDataBase:RoomDatabase() {
    abstract fun getSeriesDao(): SeriesDao
}