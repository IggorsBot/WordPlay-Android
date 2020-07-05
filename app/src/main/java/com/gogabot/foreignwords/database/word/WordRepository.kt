package com.gogabot.foreignwords.database.word

import androidx.lifecycle.LiveData
import javax.inject.Inject



public interface WordRepository {
    fun alphabetizedWords(): LiveData<List<Word>>
    fun getWordsOfDictionary(id: Int): LiveData<List<Word>>

    suspend fun insert(word: Word)
}