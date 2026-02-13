package com.challenge.myapplication.ui.base

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.challenge.myapplication.ui.theme.AndroidChallengeTheme

@Composable
internal fun EntryButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor,
        ),
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 20.sp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EntryButtonPreview() {
    AndroidChallengeTheme {
        EntryButton(
            text = "Click me!",
        )
    }
}
