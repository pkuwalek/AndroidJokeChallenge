package com.challenge.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.challenge.myapplication.navigation.NavigationEvent
import com.challenge.myapplication.navigation.Routes
import com.challenge.myapplication.ui.JokesViewModel
import com.challenge.myapplication.ui.base.EntryButton
import com.challenge.myapplication.ui.screens.singlejoke.SingleJokeDialog
import com.challenge.myapplication.ui.theme.AndroidChallengeTheme

@Composable
internal fun EntryScreen(navController: NavController) {
    val jokesViewModel: JokesViewModel = hiltViewModel()
    val uiState by jokesViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        jokesViewModel.navigationEvent.collect { event ->
            when (event) {
                NavigationEvent.NavigateToJokeList -> {
                    navController.navigate(Routes.JOKE_LIST)
                }
                NavigationEvent.NavigateBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    when  {
        uiState.error -> { ErrorScreen() }
        uiState.showContent != null -> {
            EntryScreenContent(
                onRandomJokeCtaClick = { jokesViewModel.onRandomJokeCtaClick() },
                onJokeListCtaClick = { jokesViewModel.onJokeListCtaClick() },
            )
        }
    }
    if (uiState.showRandomJokeDialog) {
        SingleJokeDialog(
            joke = uiState.showContent?.joke,
            onDismiss = { jokesViewModel.onDismissDialog() }
        )
    }
}

 @Composable
 private fun EntryScreenContent(
     onRandomJokeCtaClick: () -> Unit,
     onJokeListCtaClick: () -> Unit,
 ) {
     Column(
         modifier = Modifier
             .fillMaxSize()
             .background(color = MaterialTheme.colorScheme.background),
         verticalArrangement = Arrangement.SpaceEvenly,
         horizontalAlignment = Alignment.CenterHorizontally,
     ) {
         EntryButton(
             text = "random joke",
             onClick = onRandomJokeCtaClick,
             modifier = Modifier.fillMaxWidth(.5f),
         )
         EntryButton(
             text = "text input",
             modifier = Modifier.fillMaxWidth(.5f),
         )
         EntryButton(
             text = "joke list",
             onClick = onJokeListCtaClick,
             modifier = Modifier.fillMaxWidth(.5f),
         )
     }
 }

@Preview(showBackground = true)
@Composable
fun EntryScreenPreview() {
    AndroidChallengeTheme {
        EntryScreenContent(
            onRandomJokeCtaClick = {},
            onJokeListCtaClick = {},
        )
    }
}
