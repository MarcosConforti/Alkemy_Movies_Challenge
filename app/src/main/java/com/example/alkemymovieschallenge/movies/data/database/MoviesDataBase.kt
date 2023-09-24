package com.example.alkemymovieschallenge.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [MoviesEntities::class], version = 1)
abstract class MoviesDataBase:RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}