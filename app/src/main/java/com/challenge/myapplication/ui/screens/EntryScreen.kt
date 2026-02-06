package com.challenge.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.challenge.myapplication.ui.JokesViewModel
import com.challenge.myapplication.ui.model.JokeUiModel
import com.challenge.myapplication.ui.screens.singlejoke.SingleJokeDialog
import com.challenge.myapplication.ui.theme.AndroidChallengeTheme

@Composable
internal fun EntryScreen() {
    val jokesViewModel: JokesViewModel = viewModel()
    val uiState by jokesViewModel.uiState.collectAsState()

    when  {
        uiState.error -> { ErrorScreen() }
        uiState.showContent != null -> {
            EntryScreenContent(
                onRandomJokeCtaClick = { jokesViewModel.onRandomJokeCtaClick() }
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
 ) {
     Column(
         modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.SpaceEvenly,
         horizontalAlignment = Alignment.CenterHorizontally,
     ) {
         Button(
             onClick = {
                 onRandomJokeCtaClick()
             },
         ) {
             Text(text = "random joke")
         }
         Button(
             onClick = {
                 // TODO implement
             },
         ) {
             Text(text = "text input")
         }
         Button(
             onClick = {
                 // TODO implement
             },
         ) {
             Text(text = "joke list")
         }
     }
 }

@Composable
private fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Error, try again later.",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EntryScreenPreview() {
    AndroidChallengeTheme {
        EntryScreenContent(
            onRandomJokeCtaClick = {},
        )
    }
}
