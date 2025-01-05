package com.williamfq.xhat.di

import android.content.Context
import androidx.room.Room
import com.williamfq.data.AppDatabase
import com.williamfq.data.dao.SessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "xhat_db"

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSessionDao(appDatabase: AppDatabase): SessionDao = appDatabase.sessionDao()
}
