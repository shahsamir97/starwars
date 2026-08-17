package com.mdshahsamir.starwars

import com.mdshahsamir.starwars.presentation.FilmListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object IosKoin: KoinComponent {
    fun getFilmListViewModel(): FilmListViewModel = get()
}