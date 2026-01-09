package com.challenge.myapplication.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.challenge.myapplication.ui.JokesViewModel
import com.challenge.myapplication.ui.model.JokeUiModel
import com.challenge.myapplication.ui.theme.AndroidChallengeTheme

@Composable
internal fun EntryScreen() {
    val jokesViewModel: JokesViewModel = viewModel()
    val uiState by jokesViewModel.uiState.collectAsState()

    when  {
        uiState.loading -> { LoadingScreen() }
        uiState.error -> { ErrorScreen() }
        else -> {
            ScreenContent(
                joke = uiState.joke,
                loadNextJoke = jokesViewModel::loadNextJoke,
            )
        }
    }
}

@Composable
private fun ScreenContent(
    joke: JokeUiModel?,
    loadNextJoke: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            joke?.topText?.let {
                Text(
                    text = it,
                )
            }
            joke?.bottomText?.let {
                Text(
                    text = it,
                )
            }
            // TODO implement the logic and fix the mapping
            Button(
                onClick = {},
            ) {
                Text(text = "show punchline")
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 32.dp),
            onClick = loadNextJoke,
        ) {
            Text(
                text = "another one",
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Loading...",
        )
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
        ScreenContent(
            joke = JokeUiModel(
                topText = "To prove he was right, the flat-earther walked to the end of the Earth.",
                bottomText = "He eventually came around",
                category = "misc",
            ),
            loadNextJoke = {},
        )
    }
}
