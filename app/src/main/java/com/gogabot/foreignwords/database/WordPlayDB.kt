package com.gogabot.foreignwords.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gogabot.foreignwords.database.dictionary.Dictionary
import com.gogabot.foreignwords.database.dictionary.DictionaryDao
import com.gogabot.foreignwords.database.word.Word
import com.gogabot.foreignwords.database.word.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class, Dictionary::class], version = 1, exportSchema = false)
public abstract class WordPlayDB : RoomDatabase() {

    abstract fun getWordDao(): WordDao
    abstract fun getDictionaryDao(): DictionaryDao


    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.getWordDao(), database.getDictionaryDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao, dictionaryDao: DictionaryDao) {
            wordDao.deleteAll()
            dictionaryDao.deleteAll()

            var dictionary = Dictionary (
                    1,
                    "FirstDictionary"
                )
            dictionaryDao.insert(dictionary)

            var word = Word(
                1,
                "Бежать",
                "Run",
                "Run beach, RUN!",
                1
            )
            wordDao.insert(word)

            word = Word(
                2,
                "Прыгать",
                "Jump",
                "Jump, Jump",
                1
            )
            wordDao.insert(word)

            word = Word(
                3,
                "Гулять",
                "Walk",
                "Walk Example",
                1
            )
            wordDao.insert(word)

            word = Word(
                4,
                "Петь",
                "Sing",
                "Sing example",
                1
            )
            wordDao.insert(word)

            word = Word(
                5,
                "Песня",
                "Song",
                "Song example",
                1
            )
            wordDao.insert(word)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: WordPlayDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WordPlayDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, WordPlayDB::class.java, "wordplay-db")
                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}