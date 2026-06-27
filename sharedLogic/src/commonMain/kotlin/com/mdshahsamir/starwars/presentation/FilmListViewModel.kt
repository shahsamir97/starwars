package com.mdshahsamir.starwars.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Logger.Companion.log
import com.mdshahsamir.starwars.domain.usecases.GetFilmsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmListViewModel(
    private val getFilmsUseCase: GetFilmsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<FilmListUiState>(FilmListUiState.Loading)
    val uiState: StateFlow<FilmListUiState> = _uiState

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            _uiState.value = FilmListUiState.Loading
            try {
                val films = getFilmsUseCase()
                films.forEach {
                    Logger.d("FilmListViewModel") { "Film: ${it.title}, Director: ${it.director}" }
                }
                _uiState.value = FilmListUiState.Success(films)
            } catch (e: Exception) {
                Logger.d("FilmListViewModel") { "Failed to fetch films" }

                _uiState.value = FilmListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}