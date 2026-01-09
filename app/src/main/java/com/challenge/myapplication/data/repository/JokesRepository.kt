package com.challenge.myapplication.data.repository

import com.challenge.myapplication.common.Either
import com.challenge.myapplication.data.mapper.toDomain
import com.challenge.myapplication.data.model.JokeModel
import com.challenge.myapplication.network.JokesApiService
import javax.inject.Inject

interface JokesRepository {
    suspend fun getRandomJoke(): Either<Throwable, JokeModel>
}

class JokesRepositoryImpl @Inject constructor(
    private val jokesApiService: JokesApiService,
) : JokesRepository {


    override suspend fun getRandomJoke(): Either<Throwable, JokeModel> {
        return try {
            val dto = jokesApiService.getRandomJoke()
            Either.Right(dto.toDomain())
        } catch (e: Exception) {
            Either.Left(e)
        }
    }
}
