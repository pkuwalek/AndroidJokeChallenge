package com.challenge.myapplication.data.usecase

import com.challenge.myapplication.common.Either
import com.challenge.myapplication.data.repository.JokesRepository
import javax.inject.Inject

internal class GetJokeUseCase @Inject constructor(
    private val jokesRepository: JokesRepository,
) {
    suspend operator fun invoke(): Either<Throwable, String> {
        return jokesRepository.getRandomJoke()
    }
}
