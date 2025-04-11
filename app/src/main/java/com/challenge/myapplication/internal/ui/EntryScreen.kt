package com.challenge.myapplication.internal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun EntryScreen() {
    Column {
        Text(
            text = "Welcome!"
        )
        Button(
            onClick = {},
        ) {
            Text(text = "click me")
        }
    }
}
