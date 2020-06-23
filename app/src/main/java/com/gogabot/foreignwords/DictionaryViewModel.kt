package com.gogabot.foreignwords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gogabot.foreignwords.database.EnglishWordsRoomDatabase
import com.gogabot.foreignwords.database.dictionary.DictionaryDao
import com.gogabot.foreignwords.database.dictionary.DictionaryRepository
import com.gogabot.foreignwords.database.dictionary.Dictionary


class DictionaryViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DictionaryRepository

    val dictionariesList: LiveData<List<Dictionary>>

    init {
        val dictionaryDao: DictionaryDao = EnglishWordsRoomDatabase.getDatabase(application, viewModelScope).dictionaryDao()
        repository = DictionaryRepository(dictionaryDao)
        dictionariesList = repository.dictionariesList
    }

}