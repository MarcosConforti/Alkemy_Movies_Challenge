package com.example.alkemymovieschallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alkemymovieschallenge.data.database.dao.MoviesDao
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities

@Database (entities = [MoviesEntities::class], version = 1)
abstract class MoviesDataBase:RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}