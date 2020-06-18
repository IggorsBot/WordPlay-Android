package com.gogabot.englishwords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gogabot.englishwords.database.word.Word
import com.gogabot.englishwords.database.word.WordRepository
import com.gogabot.englishwords.database.EnglishWordsRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application): AndroidViewModel(application) {
    private val repository: WordRepository

    val allWords: LiveData<List<Word>>
//    val wordHello: LiveData<String>

    init {
        val wordsDao = EnglishWordsRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository =
            WordRepository(wordsDao)
        allWords = repository.allWords
//        wordHello = repository.wordHello
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}