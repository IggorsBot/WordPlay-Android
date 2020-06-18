package com.gogabot.englishwords.database.word

import androidx.lifecycle.LiveData
import com.gogabot.englishwords.database.word.Word
import com.gogabot.englishwords.database.word.WordDao


class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()
//    val wordHello: LiveData<String> = wordDao.getWordByID()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}