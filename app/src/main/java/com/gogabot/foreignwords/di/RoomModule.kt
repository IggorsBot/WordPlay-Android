package com.gogabot.foreignwords.di

import android.app.Application
import androidx.room.Room
import com.gogabot.foreignwords.database.WordPlayDB
import com.gogabot.foreignwords.database.dictionary.DictionaryDao
import com.gogabot.foreignwords.database.dictionary.DictionaryDataSource
import com.gogabot.foreignwords.database.dictionary.DictionaryRepository
import com.gogabot.foreignwords.database.word.WordDao
import com.gogabot.foreignwords.database.word.WordDataSource
import com.gogabot.foreignwords.database.word.WordRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
public class RoomModule(application: Application) {
    private var wordPlayDB: WordPlayDB =
        Room.databaseBuilder(application, WordPlayDB::class.java, "wordplay-db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): WordPlayDB {
        return wordPlayDB
    }

    @Singleton
    @Provides
    fun providesWordDao(): WordDao {
        return wordPlayDB.getWordDao()
    }

    @Singleton
    @Provides
    fun providesDictionaryDao(): DictionaryDao {
        return wordPlayDB.getDictionaryDao()
    }

    @Singleton
    @Provides
    fun wordRepository(): WordRepository? {
        return WordDataSource(wordPlayDB.getWordDao())
    }

    @Singleton
    @Provides
    fun dictionaryRepository(): DictionaryRepository? {
        return DictionaryDataSource(wordPlayDB.getDictionaryDao())
    }
}