package com.example.alkemymovieschallenge.core.di

import com.example.alkemymovieschallenge.core.interceptor.APIKeyInterceptor
import com.example.alkemymovieschallenge.detail.data.api.APIDetailService
import com.example.alkemymovieschallenge.movies.data.api.APIMovieService
import com.example.alkemymovieschallenge.series.data.api.APISeriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)//alcance a nivel de toda la aplicacion
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        //registra las solicitudes y respuestas HTTP realizadas por la aplicación.
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // Crea el interceptor de clave de API
        val apiKeyInterceptor = APIKeyInterceptor()

        // Crea la instancia de OkHttpClient y agrega los interceptores
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor) // Agrega el interceptor de clave de API
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPISeriesService(retrofit: Retrofit): APISeriesService {
        return retrofit.create(APISeriesService::class.java)
    }

    @Singleton
    @Provides
    fun provideAPIMoviesService(retrofit: Retrofit):APIMovieService{
        return retrofit.create(APIMovieService::class.java)
    }

    @Singleton
    @Provides
    fun providesAPIDetailService(retrofit: Retrofit):APIDetailService{
        return retrofit.create(APIDetailService::class.java)
    }
}