package com.example.finalskillcinema.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.finalskillcinema.api.CinemaApi
import com.example.finalskillcinema.api.old.KinopoiskApi

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    fun provideAPI(): KinopoiskApi {
        return KinopoiskApi.getInstance()
    }

    @Provides
    fun provideCinemaAPI(): CinemaApi {
        return CinemaApi.getInstance()
    }
}