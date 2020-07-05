package com.gogabot.foreignwords.database.dictionary

import androidx.lifecycle.LiveData


public interface DictionaryRepository {
    fun alphabetizedDictionaries(): LiveData<List<Dictionary>>
    suspend fun insert(dictionary: Dictionary)
}