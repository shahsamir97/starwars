package com.mdshahsamir.starwars.data.repository

import com.mdshahsamir.starwars.GetAllFilmsQuery
import com.mdshahsamir.starwars.data.remote.StarWarsApi
import com.mdshahsamir.starwars.domain.repository.FilmRepository

class FilmRepositoryImpl(
    private val starWarsApi: StarWarsApi
): FilmRepository {

    override suspend fun getFilms(): List<GetAllFilmsQuery.Film> {
      return starWarsApi.getFilms()
    }
}