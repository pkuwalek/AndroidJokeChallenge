package com.challenge.myapplication.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {
    @Serializable
    data object Entry: Route, NavKey

    @Serializable
    data object JokeList: Route, NavKey
}