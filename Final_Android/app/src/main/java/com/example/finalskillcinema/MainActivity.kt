package com.example.finalskillcinema

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.finalskillcinema.databinding.ActivityMainBinding
import com.example.finalskillcinema.ui.intro.IntroFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Registration token", "Создание Активности")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHost.navController

//        navController.navigate(R.id.main_container)

        PreferenceManager.getDefaultSharedPreferences(this).apply {
            Log.d("Registration token", "Проверка префов")

            if (!getBoolean(IntroFragment.INTRO_PREFS_NAME, false)) {
                Log.d("Registration token", getBoolean(IntroFragment.INTRO_PREFS_NAME, false).toString())
                navController.navigate(R.id.introFragment)
            } else {
                Log.d("Registration token", getBoolean(IntroFragment.INTRO_PREFS_NAME, false).toString())
                Log.d("Registration token", "Вход в меин активити")
                navController.navigate(R.id.mainFragment)
            }
        }
    }
}


