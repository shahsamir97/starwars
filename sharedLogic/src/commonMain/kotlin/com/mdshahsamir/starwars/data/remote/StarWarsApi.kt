package com.mdshahsamir.starwars.data.remote

import com.mdshahsamir.starwars.network.GraphQLClient

class StarWarsApi(
    private val graphQLClient: GraphQLClient
) {

    suspend fun getAllFilms(): List<FilmDto> {
        val query = """
            query AllFilms {
              allFilms {
                films {
                  id
                  title
                  director
                }
              }
            }
        """.trimIndent()

        // Executing the query and mapping to GraphQLResponse
        val response: GraphQLResponse = graphQLClient.execute(query)
        return response.data.allFilms.films
    }
}