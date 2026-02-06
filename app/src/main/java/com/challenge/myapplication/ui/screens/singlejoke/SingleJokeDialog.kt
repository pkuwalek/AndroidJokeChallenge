package com.challenge.myapplication.ui.screens.singlejoke

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.myapplication.ui.model.JokeUiModel
import com.challenge.myapplication.ui.theme.AndroidChallengeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SingleJokeDialog(
    joke: JokeUiModel?,
    onDismiss: () -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = {
            onDismiss()
        },
    ) {
        JokeDialogContent(
            joke = joke,
            onDismiss = onDismiss,
        )
    }
}

@Composable
private fun JokeDialogContent(
    joke: JokeUiModel?,
    onDismiss: () -> Unit,
) {
    var punchlineVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Cyan)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            onClick = {
                onDismiss()
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "close",
                tint = Color.Black,
                modifier = Modifier.size(24.dp),
            )
        }
        joke?.topText?.let {
            Text(
                text = it,
            )
        }
        joke?.bottomText?.let {
            if (!punchlineVisible) {
                Button(
                    onClick = {
                        punchlineVisible = true
                    },
                ) {
                    Text(text = "show punchline")
                }
            }
            if (punchlineVisible) {
                Text(
                    text = it,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleJokeDialogPreview() {
    AndroidChallengeTheme {
        SingleJokeDialog(
            joke = JokeUiModel(
                topText = "To prove he was right, the flat-earther walked to the end of the Earth.",
                bottomText = "He eventually came around",
                category = "misc",
            ),
            onDismiss = {},
        )
    }
}

