package com.mdshahsamir.starwars.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val id: String,
    val title: String,
    val director: String
)
