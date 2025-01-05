package com.williamfq.data

import android.content.Context
import androidx.room.Room
import com.williamfq.data.dao.StoryDao
import com.williamfq.data.repository.PanicAlertRepositoryImpl
import com.williamfq.data.location.LocationTrackerImpl
import com.williamfq.domain.repository.PanicAlertRepository
import com.williamfq.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Módulo para las dependencias con métodos @Provides
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "xhat-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStoryDao(database: AppDatabase): StoryDao {
        return database.storyDao()
    }
}

// Módulo para las dependencias con métodos @Binds
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPanicAlertRepository(
        panicAlertRepositoryImpl: PanicAlertRepositoryImpl
    ): PanicAlertRepository

    @Binds
    @Singleton
    abstract fun bindLocationTracker(
        locationTrackerImpl: LocationTrackerImpl
    ): LocationTracker

    // Puedes añadir más bindings aquí
}
