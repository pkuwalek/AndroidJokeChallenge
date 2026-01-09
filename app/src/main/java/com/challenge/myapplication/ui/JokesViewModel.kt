package com.challenge.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.myapplication.data.usecase.GetJokeUseCase
import com.challenge.myapplication.ui.mapper.JokeUiModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class JokesViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeUseCase,
    private val uiModelMapper: JokeUiModelMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow(JokesScreenState())
    internal val uiState by lazy {
        getRandomJoke()
        _uiState.asStateFlow()
    }

    fun loadNextJoke() {
        getRandomJoke()
    }

    private fun getRandomJoke() {
        _uiState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            getJokeUseCase.invoke().ifRight { joke ->
                _uiState.update {
                    it.copy(
                        loading = false,
                        joke = uiModelMapper.mapJokeToUiModel(joke),
                    )
                }
            }.ifLeft { _ ->
                _uiState.update {
                    it.copy(error = true)
                }
            }

        }
    }
}
