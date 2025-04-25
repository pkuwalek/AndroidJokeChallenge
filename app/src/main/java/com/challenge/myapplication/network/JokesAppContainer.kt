package com.challenge.myapplication.network

import com.challenge.myapplication.data.JokesRepository
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
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(apiUrl)
        .client(client)
        .build()
        .create(JokesApiService::class.java)

    override val jokesRepository: JokesRepository
        get() = TODO("Not yet implemented")
}
