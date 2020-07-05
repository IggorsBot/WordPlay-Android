package com.gogabot.foreignwords.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gogabot.foreignwords.database.word.Word
import com.gogabot.foreignwords.database.word.WordRepository
import com.gogabot.foreignwords.database.WordPlayDB
import com.gogabot.foreignwords.database.word.WordDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordViewModel(application: Application): AndroidViewModel(application) {
    @set:Inject
    var wordRepository: WordRepository

    private val wordsFromAllDicionaries: LiveData<List<Word>>

    init {
        val wordsDao = WordPlayDB.getDatabase(application, viewModelScope).getWordDao()
        wordRepository = WordDataSource(wordsDao)
        wordsFromAllDicionaries = wordRepository.alphabetizedWords()
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        wordRepository.insert(word)
    }

    fun getWordsOfDictionary(id: Int): LiveData<List<Word>> {
       return wordRepository.getWordsOfDictionary(id)
    }
}