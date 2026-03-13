package com.challenge.myapplication.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.challenge.myapplication.ui.model.JokeUiModel
import com.challenge.myapplication.ui.screens.ErrorScreen
import com.challenge.myapplication.ui.theme.AndroidChallengeTheme

@Composable
fun JokeListScreen(
    onNavigateBack: () -> Unit,
) {
    val jokeListViewModel: JokeListViewModel = hiltViewModel()
    val uiState by jokeListViewModel.uiState.collectAsState()

    when {
        uiState.error -> ErrorScreen()
        else -> JokeList(
            jokeList = uiState.jokesList,
            onNavigateBack = onNavigateBack,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JokeList(
    jokeList: List<JokeUiModel>?,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigateBack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "close",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
                title = {
                    Text("Jokes list")
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            jokeList?.forEach { joke ->
                JokeCard(joke = joke)
            }
        }
    }
}

@Composable
private fun JokeCard(
    joke: JokeUiModel,
) {
    var punchlineVisible by remember(joke.id) { mutableStateOf(false) }
    val dialogShape = RoundedCornerShape(12.dp)

    Card(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = dialogShape,
                )
                .border(2.dp, MaterialTheme.colorScheme.primary, dialogShape)
                .padding(48.dp)
                .clip(shape = dialogShape),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            joke.topText?.let {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = it,
                )
            }
            joke.bottomText?.let {
                if (!punchlineVisible) {
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
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
}

@Preview(showBackground = true)
@Composable
fun SingleTwoPartJokeDialogPreview() {
    AndroidChallengeTheme {
        JokeCard(
            joke = JokeUiModel(
                id = 0,
                topText = "To prove he was right, the flat-earther walked to the end of the Earth.",
                bottomText = "He eventually came around",
                category = "misc",
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingleOnePartJokeDialogPreview() {
    AndroidChallengeTheme {
        JokeCard(
            joke = JokeUiModel(
                id = 0,
                topText = "To prove he was right, the flat-earther walked to the end of the Earth.",
                bottomText = null,
                category = "misc",
            ),
        )
    }
}
