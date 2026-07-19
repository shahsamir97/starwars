package com.mdshahsamir.starwars.data

import com.mdshahsamir.starwars.GetAllFilmsQuery
import com.mdshahsamir.starwars.data.repository.FilmRepository

val fakeListOfFilms = listOf(
    GetAllFilmsQuery.Film(
        title = "A New Hope",
        releaseDate = "1977-05-25"
    ),
    GetAllFilmsQuery.Film(
        title = "The Empire Strikes Back",
        releaseDate = "1980-05-21"
    ),
    GetAllFilmsQuery.Film(
        title = "Return of the Jedi",
        releaseDate = "1983-05-25"
    )
)

class FakeFilmRepository: FilmRepository {

    override suspend fun getFilms(): List<GetAllFilmsQuery.Film> {
        return fakeListOfFilms
    }
}
