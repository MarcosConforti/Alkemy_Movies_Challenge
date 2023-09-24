package com.example.alkemymovieschallenge.series.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [SeriesEntities::class], version = 1)
abstract class SeriesDataBase:RoomDatabase() {
    abstract fun getSeriesDao(): SeriesDao
}