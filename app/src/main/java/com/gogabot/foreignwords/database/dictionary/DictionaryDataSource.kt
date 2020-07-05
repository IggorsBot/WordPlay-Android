package com.gogabot.foreignwords.database.dictionary

import androidx.lifecycle.LiveData

import javax.inject.Inject

public class DictionaryDataSource @Inject constructor(var dictionaryDao: DictionaryDao): DictionaryRepository {

    override fun alphabetizedDictionaries(): LiveData<List<Dictionary>> {
        return dictionaryDao.getAlphabetizedDictionaries()
    }

    override suspend fun insert(dictionary: Dictionary) {
        dictionaryDao.insert(dictionary)
    }
}
