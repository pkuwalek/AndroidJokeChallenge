package com.challenge.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.challenge.myapplication.ui.screens.EntryScreen
import com.challenge.myapplication.ui.theme.AndroidChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidChallengeTheme {
                EntryScreen()
            }
        }
    }
}
