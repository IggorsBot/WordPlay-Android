package com.gogabot.foreignwords.database.word

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gogabot.foreignwords.database.word.Word
import java.util.*

@Dao
interface WordDao {
    @Query("SELECT * from word_table ORDER BY word_en ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("SELECT * from word_table WHERE id=:id")
    fun getWordByID(id: Int): LiveData<Word>

    @Query("SELECT * from word_table WHERE dictionary=:id")
    fun getWordsOfDictionary(id: Int): LiveData<List<Word>>
}