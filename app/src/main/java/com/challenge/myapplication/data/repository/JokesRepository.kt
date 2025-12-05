package com.challenge.myapplication.data.repository

import com.challenge.myapplication.common.Either
import com.challenge.myapplication.network.JokesApiService
import com.challenge.myapplication.network.model.TypeDto
import javax.inject.Inject

interface JokesRepository {
    suspend fun getRandomJoke(): Either<Throwable, String>
}

class JokesRepositoryImpl @Inject constructor(
    private val jokesApiService: JokesApiService,
) : JokesRepository {


    override suspend fun getRandomJoke(): Either<Throwable, String> {
        return try {
            val dto = jokesApiService.getRandomJoke()
            val joke = when (dto.type) {
                TypeDto.SINGLE -> dto.joke ?: "No joke available"
                TypeDto.TWOPART -> "${dto.setup}\n${dto.delivery}"
            }
            Either.Right(joke)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }
}
