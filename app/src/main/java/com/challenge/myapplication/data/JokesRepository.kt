package com.challenge.myapplication.data

import com.challenge.myapplication.network.JokesApiService
import com.challenge.myapplication.network.model.JokeDto
import com.challenge.myapplication.network.model.TypeDto
import javax.inject.Inject

interface JokesRepository {
    suspend fun getRandomJoke(): String
}

class JokesRepositoryImpl @Inject constructor(
    private val jokesApiService: JokesApiService,
) : JokesRepository {


    override suspend fun getRandomJoke(): String {
        val dto = jokesApiService.getRandomJoke()

        return when (dto.type) {
            TypeDto.SINGLE -> dto.joke ?: "No joke available"
            TypeDto.TWOPART -> "${dto.setup}\n${dto.delivery}"
        }
    }
}
