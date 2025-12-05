package com.challenge.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.challenge.myapplication.ui.JokesViewModel

@Composable
internal fun EntryScreen() {
    val jokesViewModel: JokesViewModel = viewModel(factory = JokesViewModel.Factory)
    val uiState by jokesViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = uiState.randomJoke,
        )
        Button(
            onClick = {},
        ) {
            Text(text = "click me")
        }
    }
}
