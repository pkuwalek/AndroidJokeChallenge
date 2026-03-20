package com.challenge.myapplication.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.myapplication.data.usecase.GetJokesListUseCase
import com.challenge.myapplication.ui.mapper.JokeUiModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class JokeListViewModel @Inject constructor(
    private val getJokesListUseCase: GetJokesListUseCase,
    private val uiModelMapper: JokeUiModelMapper,
) : ViewModel(){

    private val _uiState = MutableStateFlow(JokesListScreenState())
    internal val uiState by lazy {
        initScreen()
        _uiState.asStateFlow()
    }

    private fun initScreen() {
        onLoadJokes()
    }

    fun onLoadJokes() {
        viewModelScope.launch {
            getJokesListUseCase.invoke().ifRight { jokes ->
                _uiState.update {
                    it.copy(
                        jokesList = it.jokesList.orEmpty() + jokes.map { joke -> uiModelMapper.mapJokeToUiModel(joke) },
                    )
                }
            }.ifLeft { _ ->
                _uiState.update { it.copy(error = true) }
            }
        }
    }
}
