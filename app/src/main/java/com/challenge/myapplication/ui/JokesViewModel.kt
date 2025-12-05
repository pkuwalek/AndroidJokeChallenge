package com.challenge.myapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.challenge.myapplication.JokesApplication
import com.challenge.myapplication.data.JokesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class JokesViewModel(
    private val jokesRepository: JokesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(JokesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getRandomJoke()
    }

    private fun getRandomJoke() {
        viewModelScope.launch {
            val randomJokeResult = jokesRepository.getRandomJoke()
            Log.i("Paula", "joke from repo: $randomJokeResult")
            _uiState.update {
                it.copy(randomJoke = randomJokeResult)
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as JokesApplication)
                val jokesRepository = application.container.jokesRepository
                JokesViewModel(jokesRepository = jokesRepository)
            }
        }
    }
}
