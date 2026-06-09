package com.mdshahsamir.starwars.data.repository

import com.mdshahsamir.starwars.domain.model.Film


interface FilmRepository {
    suspend fun getAllFilms(): List<Film>
}