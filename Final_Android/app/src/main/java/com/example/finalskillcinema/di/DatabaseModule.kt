package com.example.finalskillcinema.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.finalskillcinema.db.old.AppDataBase
import com.example.finalskillcinema.db.CinemaDao
import com.example.finalskillcinema.db.CinemaDatabase
import com.example.finalskillcinema.db.old.FilmDao

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "Films"
        ).build()
    }

    @Provides
    fun provideDao(appDataBase: AppDataBase): FilmDao {
        return appDataBase.filmDao()
    }

    @Provides
    fun provideCinemaDatabase(@ApplicationContext context: Context): CinemaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CinemaDatabase::class.java,
            "Cinema.db"
        ).build()
    }

    @Provides
    fun provideCinemaDao(database: CinemaDatabase): CinemaDao {
        return database.cinemaDao()
    }
}