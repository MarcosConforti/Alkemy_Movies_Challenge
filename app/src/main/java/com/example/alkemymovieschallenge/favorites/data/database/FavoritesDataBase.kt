package com.example.alkemymovieschallenge.favorites.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [FavoritesEntities::class], version = 1)
abstract class FavoritesDataBase:RoomDatabase() {
    abstract fun getFavoritesDao(): FavoritesDao
}