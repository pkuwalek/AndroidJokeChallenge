package com.challenge.myapplication.network

import com.challenge.myapplication.network.model.JokeDto
import com.challenge.myapplication.network.model.JokesListResponseDto
import retrofit2.http.GET

interface JokesApiService {
    @GET("joke/Any")
    suspend fun getRandomJoke(): JokeDto

    @GET("joke/Any?amount=10")
    suspend fun getJokesList(): JokesListResponseDto
}
