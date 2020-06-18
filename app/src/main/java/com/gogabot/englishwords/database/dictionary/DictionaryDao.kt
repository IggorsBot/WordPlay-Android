package com.gogabot.englishwords.database.dictionary

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface DictionaryDao {
    @Query("SELECT * from dictionary_table ORDER BY name ASC")
    fun getAlphabetizedDictionaries(): LiveData<List<Dictionary>>

    @Query("SELECT * from dictionary_table WHERE id=:id")
    fun getDictionaryByID(id: Int): LiveData<Dictionary>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dictionary: Dictionary)

    @Query("DELETE FROM dictionary_table")
    suspend fun deleteAll()
}