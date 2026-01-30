package com.challenge.myapplication.ui.mapper

import com.challenge.myapplication.data.model.JokeModel
import com.challenge.myapplication.ui.model.JokeUiModel
import javax.inject.Inject

class JokeUiModelMapper @Inject constructor() {
    fun mapJokeToUiModel(joke: JokeModel): JokeUiModel {
        val topText = when (joke.type.name.lowercase()) {
            "single" -> { joke.joke }
            "twopart" -> {
                if (joke.setup?.endsWith("?") == true) {
                    joke.setup
                }
                else { "${joke.setup}\n${joke.punchline}" }
            }
            else -> { null }
        }
        val bottomText = if (joke.type.name.lowercase() == "twopart" && joke.setup?.endsWith("?") == true) {
            joke.punchline
        } else {
            null
        }
        return JokeUiModel(
            topText =  topText,
            bottomText = bottomText,
            category = joke.category.value,
        )
    }
}