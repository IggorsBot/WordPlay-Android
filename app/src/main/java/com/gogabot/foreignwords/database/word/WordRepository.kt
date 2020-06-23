package com.gogabot.foreignwords.database.word

import androidx.lifecycle.LiveData


class WordRepository(private val wordDao: WordDao) {
    val wordsFromAllDicionaries: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    fun getWordsOfDictionary(id: Int): LiveData<List<Word>> {
        return wordDao.getWordsOfDictionary(id)
    }

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}