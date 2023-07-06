package com.example.finalskillcinema.db.old

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        FilmsCache::class,
        FilmInDB::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}