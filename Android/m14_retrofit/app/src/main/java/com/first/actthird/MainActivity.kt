package com.first.actthird

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.first.actthird.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            val search = SearchAPI.searchDataApi.getPeopleInfo()
            val url = search.body()?.results?.first()?.picture?.large.toString()
            val path = binding.image

            Glide.with(this@MainActivity)
                .load(url)
                .into(path)
            binding.firstnameText.text = "ИМЯ: " + search.body()?.results?.first()?.name?.first.toString()
            binding.lastnameText.text = "ФАМИЛИЯ: " + search.body()?.results?.first()?.name?.last.toString()
            binding.emailText.text = "E-MAIL: " + search.body()?.results?.first()?.email.toString()
            binding.birhdayText.text = "ДАТА РОЖДЕНИЯ: " + search.body()?.results?.first()?.dob?.date.toString()
            binding.phoneText.text = "ТЕЛЕФОН: " + search.body()?.results?.first()?.phone.toString()
            binding.adressText.text = "АДРЕС: " + search.body()?.results?.first()?.location?.city.toString()
        }




        binding.button.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                val search = SearchAPI.searchDataApi.getPeopleInfo()
                val url = search.body()?.results?.first()?.picture?.large.toString()
                val path = binding.image

                Glide.with(this@MainActivity)
                    .load(url)
                    .into(path)
                binding.firstnameText.text = "ИМЯ: " + search.body()?.results?.first()?.name?.first.toString()
                binding.lastnameText.text = "ФАМИЛИЯ: " + search.body()?.results?.first()?.name?.last.toString()
                binding.emailText.text = "E-MAIL: " + search.body()?.results?.first()?.email.toString()
                binding.birhdayText.text = "ДАТА РОЖДЕНИЯ: " + search.body()?.results?.first()?.dob?.date.toString()
                binding.phoneText.text = "ТЕЛЕФОН: " + search.body()?.results?.first()?.phone.toString()
                binding.adressText.text = "АДРЕС: " + search.body()?.results?.first()?.location?.city.toString()

            }


        }



    }
}



