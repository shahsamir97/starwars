package com.mdshahsamir.starwars.data.remote

import com.mdshahsamir.starwars.domain.model.Film
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    val id: String,
    val title: String,
    val director: String
)

fun FilmDto.toDomain() =
    Film(
        id = id,
        title = title,
        director = director
    )