package com.challenge.myapplication.ui

import com.challenge.myapplication.ui.model.JokeUiModel

internal data class JokesScreenState(
    val joke: JokeUiModel? = null,
    val loading: Boolean = false,
    val error: Boolean = false,
)
