package com.example.alkemymovieschallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alkemymovieschallenge.data.database.dao.FavoritesDao
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities

@Database (entities = [FavoritesEntities::class], version = 1)
abstract class FavoritesDataBase:RoomDatabase() {
    abstract fun getFavoritesDao(): FavoritesDao
}