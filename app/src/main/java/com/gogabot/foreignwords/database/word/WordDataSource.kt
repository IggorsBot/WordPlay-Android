package com.gogabot.foreignwords.database.word

import androidx.lifecycle.LiveData
import javax.inject.Inject

public class WordDataSource @Inject constructor(var wordDao: WordDao): WordRepository {

    override fun alphabetizedWords(): LiveData<List<Word>> {
        return wordDao.getAlphabetizedWords()
    }

    override fun getWordsOfDictionary(id: Int): LiveData<List<Word>> {
        return wordDao.getWordsOfDictionary(id)
    }

    override suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
