package com.challenge.myapplication.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Sorry, we're experiencing issues, please try again later.",
        )
    }
}
