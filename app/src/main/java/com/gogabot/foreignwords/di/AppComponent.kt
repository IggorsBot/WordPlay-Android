package com.gogabot.foreignwords.di

import android.app.Application
import com.gogabot.foreignwords.FirstFragment
import com.gogabot.foreignwords.SecondFragment
import com.gogabot.foreignwords.view.MainActivity
import com.gogabot.foreignwords.database.WordPlayDB
import com.gogabot.foreignwords.database.dictionary.DictionaryDao
import com.gogabot.foreignwords.database.word.WordDao
import com.gogabot.foreignwords.database.word.WordRepository
import com.gogabot.foreignwords.view.StudyActivity
import com.gogabot.foreignwords.view.WordListActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class])
interface AppComponent {

    // Activity
    fun inject(activity: MainActivity?)
    fun inject(activity: WordListActivity?)
    fun inject(activity: StudyActivity?)

    // Fragment
    fun inject(fragment: FirstFragment?)
    fun inject(fragment: SecondFragment?)

    fun wordDao(): WordDao?
    fun dictionaryDao(): DictionaryDao?
    fun wordPlayDB(): WordPlayDB?
    fun wordRepository(): WordRepository?
    fun application(): Application?
}