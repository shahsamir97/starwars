package com.mdshahsamir.starwars.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String,
    val variables: Map<String, String> = emptyMap()
)