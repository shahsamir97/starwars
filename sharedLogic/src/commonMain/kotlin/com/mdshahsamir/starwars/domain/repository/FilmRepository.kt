package com.mdshahsamir.starwars.domain.repository

import com.mdshahsamir.starwars.GetAllFilmsQuery

interface FilmRepository {
    suspend fun getFilms(): List<GetAllFilmsQuery.Film>
}