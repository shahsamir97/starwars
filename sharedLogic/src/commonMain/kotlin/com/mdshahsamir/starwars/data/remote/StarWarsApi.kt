package com.mdshahsamir.starwars.data.remote

import com.mdshahsamir.starwars.GetAllFilmsQuery


interface StarWarsApi {
    suspend fun getFilms(): List<GetAllFilmsQuery.Film>
}