package com.challenge.myapplication.ui

import com.challenge.myapplication.ui.model.JokeUiModel

internal data class JokesScreenState(
    val showContent: JokeScreenContent? = null,
    val error: Boolean = false,
    val showRandomJokeDialog: Boolean = false,
)

internal data class JokeScreenContent(
    val joke: JokeUiModel? = null,
)
