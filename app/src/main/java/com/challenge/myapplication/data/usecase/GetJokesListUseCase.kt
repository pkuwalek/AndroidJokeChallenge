package com.challenge.myapplication.data.usecase

import com.challenge.myapplication.common.Either
import com.challenge.myapplication.data.model.JokeModel
import com.challenge.myapplication.data.repository.JokesRepository
import javax.inject.Inject

internal class GetJokesListUseCase @Inject constructor(
    private val jokesRepository: JokesRepository,
) {
    suspend operator fun invoke(): Either<Throwable, List<JokeModel>> {
        return jokesRepository.getJokesList()
    }
}
