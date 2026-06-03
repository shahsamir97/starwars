package com.mdshahsamir.starwars.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse(
    val data: FilmsData
)

@Serializable
data class FilmsData(
    val allFilms: AllFilms
)

@Serializable
data class AllFilms(
    val films: List<FilmDto>
)