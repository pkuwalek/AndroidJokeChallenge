package com.challenge.myapplication.network

import com.challenge.myapplication.data.JokesRepository
import com.challenge.myapplication.data.JokesRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface JokesAppContainer {
    val jokesRepository: JokesRepository
}

class DefaultAppContainer : JokesAppContainer {
    private val apiUrl = "https://v2.jokeapi.dev/"

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()
    
    private val retrofitService: JokesApiService by lazy {
        retrofit.create(JokesApiService::class.java)
    }

    override val jokesRepository: JokesRepository by lazy {
        JokesRepositoryImpl(retrofitService)
    }
}
