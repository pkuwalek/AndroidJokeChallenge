package com.challenge.myapplication.network.model

import kotlinx.serialization.Serializable

@Serializable
data class JokesListResponseDto(
    val error: Boolean,
    val jokes: List<JokeDto>,
    val amount: Int,
)