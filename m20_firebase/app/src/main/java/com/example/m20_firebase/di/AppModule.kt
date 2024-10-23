package com.example.m20_firebase.di

import android.content.Context
import androidx.room.Room
import com.example.m20_firebase.data.room.AppDatabase
import com.example.m20_firebase.data.room.SightPhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return  Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Gallery"
        ).build()
    }

    @Provides
    fun provideSightPhotosDao(appDatabase: AppDatabase): SightPhotosDao = appDatabase.sightPhotosDao()
}