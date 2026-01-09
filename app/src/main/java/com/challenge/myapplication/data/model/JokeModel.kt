package com.challenge.myapplication.data.model

data class JokeModel(
    val id: Int,
    val type: JokeType,
    val category: JokeCategory,
    val joke: String?,
    val setup: String?,
    val punchline: String?,
)
