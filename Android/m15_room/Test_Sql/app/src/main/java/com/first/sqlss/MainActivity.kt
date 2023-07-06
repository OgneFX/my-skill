package com.first.sqlss


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.first.sqlss.databinding.ActivityMainBinding
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (application as App).dataBase.wordDao()
                return MainViewModel(wordDao) as T
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val regex = "^[^-\\W^\\d][a-zA-Zа-яА-Я-]+[^-\\W^\\d]$".toRegex()

        fun isValid(text: String?): Boolean {
            val pattern = Pattern.compile("^[^-\\W^\\d][a-zA-Zа-яА-Я-]+[^-\\W^\\d]$")
            val matcher = pattern.matcher(text)
            return matcher.matches()
        }

        // Кнопка добавить слово
        binding.buttonAdd.setOnClickListener {
            val text = binding.editText.text.toString()
            binding.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!isValid(text)) {
                        binding.editText.error = "Input only letter!"
                    } else {
                        binding.editText.error = null
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
            if (isValid(text)) {
                viewModel.onAddBtn(text)
            } else {
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
            }

        }


        // Кнопка удалить слова из базы данных
        binding.buttonDelete.setOnClickListener {
            viewModel.onDltBtn()
            binding.textWord.text = ""
        }


        viewModel.words.observe(this) {
            var text = ""
            for(word in it)
                text += " ${word.mainword} ${word.counter} \r\n"
            binding.textWord.text = text

        }

        viewModel.getWords()



    }
}