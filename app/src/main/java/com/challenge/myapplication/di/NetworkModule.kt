package com.challenge.myapplication.di

import com.challenge.myapplication.data.repository.JokesRepository
import com.challenge.myapplication.data.repository.JokesRepositoryImpl
import com.challenge.myapplication.network.JokesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideJokesApiService(retrofit: Retrofit): JokesApiService {
        return retrofit.create(JokesApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindJokesRepository(
        jokesRepositoryImpl: JokesRepositoryImpl
    ): JokesRepository
}