package com.challenge.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.myapplication.data.usecase.GetJokeUseCase
import com.challenge.myapplication.navigation.NavigationEvent
import com.challenge.myapplication.ui.mapper.JokeUiModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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
        initScreen()
        _uiState.asStateFlow()
    }

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private fun initScreen() {
        _uiState.update {
            it.copy(
                showContent = JokeScreenContent(),
                error = false,
            )
        }
    }

    fun onDismissDialog() {
        _uiState.update {
            it.copy(showRandomJokeDialog = false)
        }
    }

    fun onRandomJokeCtaClick() {
        viewModelScope.launch {
            getJokeUseCase.invoke().ifRight { joke ->
                _uiState.update {
                    it.copy(
                        showContent = it.showContent?.copy(joke = uiModelMapper.mapJokeToUiModel(joke)),
                        showRandomJokeDialog = true,
                    )
                }
            }.ifLeft { _ ->
                _uiState.update {
                    it.copy(error = true)
                }
            }
        }
    }

    fun onJokeListCtaClick() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.NavigateToJokeList)
        }
    }
}
