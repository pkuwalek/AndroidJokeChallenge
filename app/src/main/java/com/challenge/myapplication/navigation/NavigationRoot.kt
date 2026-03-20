package com.challenge.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.challenge.myapplication.ui.screens.EntryScreen
import com.challenge.myapplication.ui.screens.list.JokeListScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(Route.Entry)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = { key ->
            when(key){
                is Route.Entry -> {
                    NavEntry(
                        key = key,
                    ) {
                        EntryScreen(
                            onJokeListCtaClick = {
                                backStack.add(Route.JokeList)
                            }
                        )
                    }
                }
                is Route.JokeList -> {
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
