// data/src/main/java/com/williamfq/data/di/DataModule.kt
package com.williamfq.data.di

import com.williamfq.domain.repository.ChatRepository
import com.williamfq.domain.repository.PanicRepository
import com.williamfq.data.repository.ChatRepositoryImpl
import com.williamfq.data.repository.PanicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo para la inyección de dependencias relacionadas con los repositorios.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    /**
     * Vincula la implementación de PanicRepository a PanicRepositoryImpl.
     *
     * @param panicRepositoryImpl La implementación concreta del repositorio de pánico.
     * @return La interfaz PanicRepository para inyección de dependencias.
     */
    @Binds
    @Singleton
    abstract fun bindPanicRepository(
        panicRepositoryImpl: PanicRepositoryImpl
    ): PanicRepository

    /**
     * Vincula la implementación de ChatRepository a ChatRepositoryImpl.
     *
     * @param chatRepositoryImpl La implementación concreta del repositorio de chat.
     * @return La interfaz ChatRepository para inyección de dependencias.
     */
    @Binds
    @Singleton
    abstract fun bindChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository
}
