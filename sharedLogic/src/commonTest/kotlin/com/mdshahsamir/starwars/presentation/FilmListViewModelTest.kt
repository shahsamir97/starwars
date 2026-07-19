package com.mdshahsamir.starwars.presentation

import com.mdshahsamir.starwars.data.FakeFilmRepository
import com.mdshahsamir.starwars.data.fakeListOfFilms
import com.mdshahsamir.starwars.domain.usecases.GetFilmsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlin.test.Test
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.assertEquals

class FilmListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun  loadFilms_whenRepositoryReturnFilms_updateUiStateToSuccess() = runTest {
        val fakeFilmRepository = FakeFilmRepository()
        val getFilmsUseCase = GetFilmsUseCase(fakeFilmRepository)
        val viewModel = FilmListViewModel(getFilmsUseCase)

        viewModel.loadFilms()

        advanceUntilIdle()

        assertEquals(
            FilmListUiState.Success(fakeListOfFilms),
            viewModel.uiState.value
        )
    }

    @Test
    fun loadFilms_showsLoadingThenSuccess() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

    }

}