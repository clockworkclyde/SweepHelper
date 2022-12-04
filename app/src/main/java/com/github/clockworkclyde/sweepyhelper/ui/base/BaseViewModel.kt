package com.github.clockworkclyde.sweepyhelper.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<
        UiState : IViewState,
        Event : IViewEvent,
        Effect : IViewEffect> :
    ViewModel() {

    private val initialState: UiState by lazy { setInitialState() }
    abstract fun setInitialState(): UiState

    private val _viewState = MutableStateFlow<UiState>(initialState)
    val viewState = _viewState.asStateFlow()

    val currentState = _viewState.value

    private val _event = MutableSharedFlow<Event>()

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    private fun subscribeToEvents() {
        _event.onEach {
            handleEvents(it)
        }.launchIn(viewModelScope)
    }

    abstract fun handleEvents(event: Event)

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
}