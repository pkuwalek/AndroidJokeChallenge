package com.challenge.myapplication.data.repository

import com.challenge.myapplication.common.Either
import com.challenge.myapplication.data.mapper.toDomain
import com.challenge.myapplication.data.model.JokeModel
import com.challenge.myapplication.network.JokesApiService
import javax.inject.Inject

interface JokesRepository {
    suspend fun getRandomJoke(): Either<Throwable, JokeModel>
    suspend fun getJokesList(): Either<Throwable, List<JokeModel>>
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

    override suspend fun getJokesList(): Either<Throwable, List<JokeModel>> {
        return try {
            val dto = jokesApiService.getJokesList()
            Either.Right(dto.jokes.map { joke -> joke.toDomain() })
        } catch (e: Exception) {
            Either.Left(e)
        }
    }
}
