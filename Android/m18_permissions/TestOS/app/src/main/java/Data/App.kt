package Data

import android.app.Application
import androidx.room.Room


class App: Application() {
    lateinit var dataBase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "dataBase"
        ).build()

    }
}