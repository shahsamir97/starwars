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

        if (response.exception != null) {
            response.exception?.printStackTrace()
            return emptyList()
        }

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