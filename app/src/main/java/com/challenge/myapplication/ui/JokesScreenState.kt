package com.challenge.myapplication.ui

internal data class JokesScreenState(
    val randomJoke: String = "",
    val loading: Boolean = false,
    val error: Boolean = false,
)
