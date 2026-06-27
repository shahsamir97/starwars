package com.mdshahsamir.starwars.presentation

import com.mdshahsamir.starwars.domain.model.Film

sealed interface FilmListUiState {
    object Loading: FilmListUiState
    data class Success(val films: List<Film>): FilmListUiState
    data class Error(val message: String): FilmListUiState
}