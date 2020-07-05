package com.gogabot.foreignwords.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
public class AppModule @Inject constructor(var application: Application){

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }
}