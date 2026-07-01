package com.mdshahsamir.starwars.presentation

import com.mdshahsamir.starwars.GetAllFilmsQuery
import com.mdshahsamir.starwars.adapter.GetAllFilmsQuery_ResponseAdapter

sealed interface FilmListUiState {
    object Loading: FilmListUiState
    data class Success(val films: List<GetAllFilmsQuery.Film>): FilmListUiState
    data class Error(val message: String): FilmListUiState
}