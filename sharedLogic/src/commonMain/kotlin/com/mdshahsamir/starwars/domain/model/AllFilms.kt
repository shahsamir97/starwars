package com.mdshahsamir.starwars.domain.model

import com.mdshahsamir.starwars.Film
import kotlinx.serialization.Serializable

@Serializable
data class AllFilms(
    val films: List<Film>
)
