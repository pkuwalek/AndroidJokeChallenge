package com.challenge.myapplication.network

import com.challenge.myapplication.network.model.JokeDto
import retrofit2.http.GET

interface JokesApiService {
    @GET("joke/Any")
    suspend fun getRandomJoke(): JokeDto
}
