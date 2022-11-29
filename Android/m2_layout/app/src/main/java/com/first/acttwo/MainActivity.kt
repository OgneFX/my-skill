package com.first.acttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.first.acttwo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customTextOnMain.setTexttop("Верхняя строчка, настроенная из кода")
        binding.customTextOnMain.setTextbottom("Нижняя строчка, настроенная из кода")

    }
}