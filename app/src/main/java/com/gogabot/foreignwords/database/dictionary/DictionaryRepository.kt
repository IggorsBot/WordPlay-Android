package com.gogabot.foreignwords.database.dictionary

import androidx.lifecycle.LiveData

class DictionaryRepository(private val dictionaryDao: DictionaryDao) {
    val dictionaryList: LiveData<List<Dictionary>> = dictionaryDao.getAlphabetizedDictionaries()

    suspend fun insert(dictionary: Dictionary) {
        dictionaryDao.insert(dictionary)
    }
}