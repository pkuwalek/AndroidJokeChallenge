package com.challenge.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.challenge.myapplication.ui.screens.EntryScreen
import com.challenge.myapplication.ui.screens.list.JokeListScreen
import kotlinx.serialization.Serializable

@Serializable
data object EntryScreen: NavKey

@Serializable
data object JokeListScreen: NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(EntryScreen)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = { key ->
            when(key){
                is EntryScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        EntryScreen(
                            onJokeListCtaClick = {
                                backStack.add(JokeListScreen)
                            }
                        )
                    }
                }
                is JokeListScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        JokeListScreen(
                            onNavigateBack = {
                                backStack.removeLastOrNull()
                            }
                        )
                    }
            }
                else -> throw IllegalArgumentException("Unknown NavKey: $key")
            }
        },
    )
}
