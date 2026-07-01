package com.mdshahsamir.starwars.data.repository

import com.mdshahsamir.starwars.GetAllFilmsQuery


interface FilmRepository {
    suspend fun getFilms(): List<GetAllFilmsQuery.Film>
}