package com.joselaine.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.joselaine.marvelapp.data.db.AppDatabase
import com.joselaine.marvelapp.data.db.DataBaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DataBaseConstants.APP_DATABASE_NAME
    ).build()

    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase) = appDatabase.characterDao()
}