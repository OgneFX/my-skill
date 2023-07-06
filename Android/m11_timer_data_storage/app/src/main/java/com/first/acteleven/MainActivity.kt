package com.first.acteleven

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.first.acteleven.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)

        binding.mainText.text = repository.getText(this)

        binding.buttonSave.setOnClickListener {

            repository.saveText(binding.editText.text.toString())
            binding.mainText.text = repository.getText(this)

        }

        binding.buttonClear.setOnClickListener {
            repository.clearText()
            binding.mainText.text = ""

        }




    }
}