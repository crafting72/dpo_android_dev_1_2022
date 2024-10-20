package com.example.m15_room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val wordsDao: WordsDao) : ViewModel() {

    val allWords = this.wordsDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    private fun update(word: String){
        viewModelScope.launch {
            allWords.value.map {
                val words = it.copy()
                if (word == words.word) words.count += 1
                wordsDao.update(words)
            }
        }
    }
    fun onAddBtn(word: String) {
        viewModelScope.launch {
            if (wordsDao.getCountWords(word) == null) wordsDao.insert(
                Words(
                    word = word,
                    count = 1
                )
            )
            else update(word)
        }
    }

    fun onDelBtn(){
        viewModelScope.launch {
            allWords.value.forEach{ wordsDao.delete(it) }
        }
    }
}