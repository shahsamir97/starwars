package com.mdshahsamir.starwars.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AllFilms(
    val films: List<Film>
)
