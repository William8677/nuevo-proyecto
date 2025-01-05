package com.williamfq.data.di

import com.williamfq.data.repository.PanicRepositoryImpl
import com.williamfq.data.repository.UserActivityRepositoryImpl
import com.williamfq.domain.repository.PanicRepository
import com.williamfq.domain.repository.UserActivityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserActivityRepository(
        impl: UserActivityRepositoryImpl
    ): UserActivityRepository

    @Binds
    @Singleton
    abstract fun bindPanicRepository(
        impl: PanicRepositoryImpl
    ): PanicRepository
}
