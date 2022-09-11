package com.example.alkemymovieschallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alkemymovieschallenge.data.database.dao.TvDao
import com.example.alkemymovieschallenge.data.database.entities.TvEntities

@Database (entities = [TvEntities::class], version = 1)
abstract class TvDataBase:RoomDatabase() {
    abstract fun getTvDao(): TvDao
}