package com.mdshahsamir.starwars.data.remote

import co.touchlab.kermit.Logger
import com.apollographql.apollo.api.ApolloRequest
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

val apolloKermitInterceptor = object : ApolloInterceptor {
    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {
        Logger.withTag("Apollo").i("Request: ${request.operation.name()}")

        return chain.proceed(request).onEach { response ->
            Logger.withTag("Apollo").i {
                "GraphQL response: ${response.data ?: "null"}"
            }

            response.exception?.let { ex ->
                Logger.withTag("Apollo").e(ex) {
                    "GraphQL error for ${request.operation.name()}"
                }
            }
        }
    }
}