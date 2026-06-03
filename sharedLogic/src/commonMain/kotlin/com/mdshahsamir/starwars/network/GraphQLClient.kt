package com.mdshahsamir.starwars.network

import com.mdshahsamir.starwars.data.remote.GraphQLRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

class GraphQLClient(
) {

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend inline fun <reified T> execute(
        query: String
    ): T {

        return httpClient.post(
            "https://swapi-graphql.netlify.app/.netlify/functions/index"
        ) {
            contentType(ContentType.Application.Json)

            setBody(
                GraphQLRequest(query)
            )
        }.body()
    }
}