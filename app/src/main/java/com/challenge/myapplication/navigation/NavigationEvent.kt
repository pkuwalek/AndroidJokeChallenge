package com.challenge.myapplication.navigation

sealed class NavigationEvent {
    object NavigateToJokeList : NavigationEvent()
    object NavigateBack : NavigationEvent()
}