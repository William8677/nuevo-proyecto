package com.williamfq.xhat.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/") // Reemplaza con tu URL base
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Aquí puedes agregar métodos para proporcionar instancias de tus servicios de Retrofit
    // Ejemplo:
    // @Provides
    // @Singleton
    // fun provideExampleService(retrofit: Retrofit): ExampleService {
    //     return retrofit.create(ExampleService::class.java)
    // }
}
