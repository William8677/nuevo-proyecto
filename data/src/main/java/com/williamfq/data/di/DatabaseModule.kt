package com.williamfq.data.di

import android.content.Context
import com.williamfq.data.AppDatabase
import com.williamfq.data.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Proporciona la instancia de `AppDatabase`.
     *
     * @param context Contexto de la aplicación.
     * @return Instancia de `AppDatabase`.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    // DAOs

    @Provides
    fun provideChatMessageDao(database: AppDatabase): ChatMessageDao {
        return database.chatMessageDao()
    }

    @Provides
    fun provideStoryDao(database: AppDatabase): StoryDao {
        return database.storyDao()
    }

    @Provides
    fun provideCommunityDao(database: AppDatabase): CommunityDao {
        return database.communityDao()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideChannelDao(database: AppDatabase): ChannelDao {
        return database.channelDao()
    }

    @Provides
    fun provideReactionDao(database: AppDatabase): ReactionDao {
        return database.reactionDao()
    }

    @Provides
    fun provideCallHistoryDao(database: AppDatabase): CallHistoryDao {
        return database.callHistoryDao()
    }

    @Provides
    fun provideSettingsDao(database: AppDatabase): SettingsDao {
        return database.settingsDao()
    }

    @Provides
    fun provideMediaDao(database: AppDatabase): MediaDao {
        return database.mediaDao()
    }

    @Provides
    fun provideNotificationDao(database: AppDatabase): NotificationDao {
        return database.notificationDao()
    }
}