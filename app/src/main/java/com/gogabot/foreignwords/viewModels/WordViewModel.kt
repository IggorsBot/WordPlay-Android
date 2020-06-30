package com.gogabot.foreignwords.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gogabot.foreignwords.database.word.Word
import com.gogabot.foreignwords.database.word.WordRepository
import com.gogabot.foreignwords.database.EnglishWordsRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application): AndroidViewModel(application) {
    private val repository: WordRepository

    val wordsFromAllDicionaries: LiveData<List<Word>>

    init {
        val wordsDao = EnglishWordsRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        wordsFromAllDicionaries = repository.wordsFromAllDicionaries
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun getWordsOfDictionary(id: Int): LiveData<List<Word>> {
       return repository.getWordsOfDictionary(id)
    }
}