package com.example.alkemymovieschallenge.core.di

import android.content.Context
import androidx.room.Room
import com.example.alkemymovieschallenge.data.database.FavoritesDataBase
import com.example.alkemymovieschallenge.data.database.MoviesDataBase
import com.example.alkemymovieschallenge.data.database.TvDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MOVIES_DATABASE_NAME = "MOVIES_DATABASE"
    private const val SERIES_DATABASE_NAME = "SERIES_DATABASE"
    private const val FAVORITES_DATABASE_NAME = "FAVORITES_DATABASE"


    @Singleton
    @Provides
    fun provideMoviesRoom(@ApplicationContext context: Context): MoviesDataBase {

        return Room.databaseBuilder(context, MoviesDataBase::class.java, MOVIES_DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(db: MoviesDataBase) = db.getMoviesDao()

    @Singleton
    @Provides
    fun provideTvRoom(@ApplicationContext context: Context): TvDataBase {

        return Room.databaseBuilder(context, TvDataBase::class.java, SERIES_DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideTvDao(db: TvDataBase) = db.getTvDao()

    @Singleton
    @Provides
    fun provideFavoritesRoom(@ApplicationContext context: Context): FavoritesDataBase {

        return Room.databaseBuilder(context, FavoritesDataBase::class.java, FAVORITES_DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoritesDao(db: FavoritesDataBase) = db.getFavoritesDao()
}