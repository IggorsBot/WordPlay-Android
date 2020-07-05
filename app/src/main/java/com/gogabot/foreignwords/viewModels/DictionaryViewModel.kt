package com.gogabot.foreignwords.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogabot.foreignwords.database.WordPlayDB
import com.gogabot.foreignwords.database.dictionary.DictionaryDao
import com.gogabot.foreignwords.database.dictionary.DictionaryRepository
import com.gogabot.foreignwords.database.dictionary.Dictionary
import com.gogabot.foreignwords.database.dictionary.DictionaryDataSource
import javax.inject.Inject

class DictionaryViewModel (application: Application): ViewModel() {
    @set:Inject
    var repository: DictionaryRepository

    val dictionaryList: LiveData<List<Dictionary>>

    init {
        val dictionaryDao: DictionaryDao = WordPlayDB.getDatabase(application, viewModelScope).getDictionaryDao()
        repository = DictionaryDataSource(dictionaryDao)
        dictionaryList = repository.alphabetizedDictionaries()
    }
}