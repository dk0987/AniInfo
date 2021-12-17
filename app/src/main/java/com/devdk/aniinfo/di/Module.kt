package com.devdk.aniinfo.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.devdk.aniinfo.common.Constants
import com.devdk.aniinfo.data.remote.AnimeAPI
import com.devdk.aniinfo.data.repository.AnimeRepositoryImpl
import com.devdk.aniinfo.domain.repository.AnimeRepository
import com.devdk.aniinfo.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideAPI() : AnimeAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api : AnimeAPI) : AnimeRepository{
        return AnimeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases1(repository: AnimeRepository) : GetAnime{
        return GetAnime(repository)
    }
    @Provides
    @Singleton
    fun provideUseCases2(repository: AnimeRepository) : GetAnimeGenre1 {
        return GetAnimeGenre1(repository)
    }
    @Provides
    @Singleton
    fun provideUseCases3(repository: AnimeRepository) : GetAnimeGenre2 {
        return GetAnimeGenre2(repository)
    }
    @Provides
    @Singleton
    fun provideUseCases4(repository: AnimeRepository) : GetAnimeGenre3{
        return GetAnimeGenre3(repository)
    }
    @Provides
    @Singleton
    fun provideUseCases5(repository: AnimeRepository) : GetAnimeById{
        return GetAnimeById(repository)
    }

    @Provides
    @Singleton
    fun provideSharedPref(app : Application) : SharedPreferences {
        return app.getSharedPreferences("Anime App" , MODE_PRIVATE)
    }

}













