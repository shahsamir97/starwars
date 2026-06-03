package com.mdshahsamir.starwars.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val title: String,
    val director: String
)
