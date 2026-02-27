package com.challenge.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.challenge.myapplication.ui.screens.EntryScreen
import com.challenge.myapplication.ui.screens.list.JokeListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.ENTRY
    ) {
        composable(Routes.ENTRY) {
            EntryScreen(navController = navController)
        }
        composable(Routes.JOKE_LIST) {
            JokeListScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}