package com.mdshahsamir.starwars.data.repository

import com.mdshahsamir.starwars.Film

interface FilmRepository {
    suspend fun getAllFilms(): List<Film>
}