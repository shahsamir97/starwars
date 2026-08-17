package com.mdshahsamir.starwars.presentation

import com.mdshahsamir.starwars.domain.usecases.GetFilmsUseCase
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


open class FilmListViewModel(
     private val getFilmsUseCase: GetFilmsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<FilmListUiState>(viewModelScope,FilmListUiState.Loading)
    val uiState: StateFlow<FilmListUiState> = _uiState.asStateFlow()

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            _uiState.value = FilmListUiState.Loading

            try {
                val films = getFilmsUseCase()
                _uiState.value = FilmListUiState.Success(films)
            } catch (e: Exception) {
                 e.printStackTrace()
                _uiState.value = FilmListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}