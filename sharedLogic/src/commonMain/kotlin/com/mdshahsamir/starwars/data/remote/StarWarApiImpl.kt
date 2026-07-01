package com.mdshahsamir.starwars.data.remote

import com.apollographql.apollo.ApolloClient
import com.mdshahsamir.starwars.GetAllFilmsQuery

class StarWarApiImpl(
    private val apolloClient: ApolloClient
): StarWarsApi {
    override suspend fun getFilms(): List<GetAllFilmsQuery.Film> {
        val response = apolloClient
            .query(GetAllFilmsQuery())
            .execute()

        // Network / HTTP / parsing failure
        if (response.exception != null) {
            response.exception?.printStackTrace()
            return emptyList()
        }

        // GraphQL server returned errors
        if (response.errors != null) {
            println(response.errors)
            return emptyList()
        }

        return response.data
            ?.allFilms
            ?.films
            .orEmpty()
            .filterNotNull()
    }
}