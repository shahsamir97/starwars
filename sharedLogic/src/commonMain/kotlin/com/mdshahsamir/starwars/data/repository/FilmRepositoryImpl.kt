package com.mdshahsamir.starwars.data.repository

import com.mdshahsamir.starwars.data.remote.StarWarsApi
import com.mdshahsamir.starwars.data.remote.toDomain
import com.mdshahsamir.starwars.domain.model.Film


class FilmRepositoryImpl(
    private val starWarsApi: StarWarsApi
): FilmRepository {

    override suspend fun getAllFilms(): List<Film> {
        return starWarsApi.getAllFilms().map { it.toDomain() }
    }
}