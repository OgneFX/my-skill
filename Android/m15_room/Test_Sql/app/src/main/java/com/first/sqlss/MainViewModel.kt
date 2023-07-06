package com.first.sqlss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {


    val words: MutableLiveData<List<Word>> = MutableLiveData<List<Word>>()
//    val getWord: StateFlow<List<String>> = this.wordDao.getMainWord()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000L),
//            initialValue = emptyList()
//
//        )

    fun getWords() {
        viewModelScope.launch {
            words.postValue(wordDao.getMainWord().first())
        }
    }


    fun onAddBtn(text: String) {
        viewModelScope.launch {
            var word: Word? = wordDao.getMainWord(text).firstOrNull()
            if (word != null) {
                word.counter++
                wordDao.update(word)
            }
            else {
                word = Word(text, 0)
                wordDao.insertNewWord(word)
            }
            getWords()
//            var switch = false
//
//            getWord.value.forEach {
//                if (it == text) {
//                    wordDao.updateCount(text)
//                    switch = true
//                }
//
//            }
//            if (!switch) {
//                wordDao.insertNewWord(
//                    Word(
//                        mainword = text,
//                        counter = 0
//
//                    )
//                )
//                switch = false
//            }


        }
    }


    fun onDltBtn() {
        viewModelScope.launch {
            wordDao.deleteTable()
        }
    }


}