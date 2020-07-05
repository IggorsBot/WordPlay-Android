package com.gogabot.foreignwords.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gogabot.foreignwords.database.WordPlayDB
import com.gogabot.foreignwords.database.word.Word
import com.gogabot.foreignwords.database.word.WordDataSource
import com.gogabot.foreignwords.database.word.WordRepository
import javax.inject.Inject

class StudyViewModel(application: Application): AndroidViewModel(application) {
    @set:Inject
    var wordRepository: WordRepository


    init {
        val wordsDao = WordPlayDB.getDatabase(application, viewModelScope).getWordDao()
        wordRepository = WordDataSource(wordsDao)
    }

    fun getWordsOfDictionary(dictionaryID: Int): LiveData<List<Word>> {
        return wordRepository.getWordsOfDictionary(dictionaryID)
    }
}