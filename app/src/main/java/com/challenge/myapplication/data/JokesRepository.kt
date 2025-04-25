package com.challenge.myapplication.data

import com.challenge.myapplication.network.JokesApiService

interface JokesRepository {
    suspend fun getRandomJoke()
}

class JokesRepositoryImpl(
    private val jokesApiService: JokesApiService,
) : JokesRepository {

    override suspend fun getRandomJoke() {
        TODO("Not yet implemented")
    }

}
