package com.challenge.myapplication.ui.screens.list

import com.challenge.myapplication.ui.model.JokeUiModel

internal data class JokesListScreenState (
    val jokesList: List<JokeUiModel>? = null,
    val error: Boolean = false,
)
