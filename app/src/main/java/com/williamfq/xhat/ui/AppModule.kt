package com.williamfq.xhat.di

import android.content.Context
import androidx.room.Room
import com.williamfq.data.dao.CommunityDao
import com.williamfq.data.local.XhatDatabase
import com.williamfq.data.remote.XhatApiService
import com.williamfq.data.repository.CommunityRepositoryImpl
import com.williamfq.domain.repository.CommunityRepository
import com.williamfq.domain.usecases.CreateCommunityUseCase
import com.williamfq.domain.usecases.GetCommunitiesUseCase
import com.williamfq.domain.usecases.SubscribeCommunityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.xhat.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideXhatApiService(retrofit: Retrofit): XhatApiService =
        retrofit.create(XhatApiService::class.java)

    @Provides
    @Singleton
    fun provideXhatDatabase(
        @ApplicationContext appContext: Context
    ): XhatDatabase = Room.databaseBuilder(
        appContext,
        XhatDatabase::class.java,
        "xhat_db"
    ).build()

    @Provides
    @Singleton
    fun provideCommunityDao(database: XhatDatabase): CommunityDao {
        return database.communityDao()
    }

    @Provides
    @Singleton
    fun provideCommunityRepository(
        communityDao: CommunityDao
    ): CommunityRepository = CommunityRepositoryImpl(communityDao)

    @Provides
    @Singleton
    fun provideGetCommunitiesUseCase(
        repository: CommunityRepository
    ): GetCommunitiesUseCase = GetCommunitiesUseCase(repository)

    @Provides
    @Singleton
    fun provideCreateCommunityUseCase(
        repository: CommunityRepository
    ): CreateCommunityUseCase = CreateCommunityUseCase(repository)

    @Provides
    @Singleton
    fun provideSubscribeCommunityUseCase(
        repository: CommunityRepository
    ): SubscribeCommunityUseCase = SubscribeCommunityUseCase(repository)
}