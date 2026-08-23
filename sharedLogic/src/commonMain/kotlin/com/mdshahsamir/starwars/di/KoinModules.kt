package com.mdshahsamir.starwars.di

import com.apollographql.apollo.ApolloClient
import com.mdshahsamir.starwars.data.remote.StarWarApiImpl
import com.mdshahsamir.starwars.data.remote.StarWarsApi
import com.mdshahsamir.starwars.data.remote.apolloKermitInterceptor
import com.mdshahsamir.starwars.domain.repository.FilmRepository
import com.mdshahsamir.starwars.data.repository.FilmRepositoryImpl
import com.mdshahsamir.starwars.domain.usecases.GetFilmsUseCase
import com.mdshahsamir.starwars.presentation.FilmListViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module

val networkModule = module {
    single { apolloKermitInterceptor }
    single {
        ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/graphql")
            .addInterceptor(apolloKermitInterceptor)
            .build()
    }
    factory<StarWarsApi> { StarWarApiImpl(get()) }
}

val repositoryModule = module {
    factory<FilmRepository> { FilmRepositoryImpl(get()) }
}

val domainModule = module {
    factory { GetFilmsUseCase(get()) }
}

val presentationModule = module {
    viewModel { params ->
        FilmListViewModel(
            getFilmsUseCase = get()
        )
    }
}

fun initKoin(config: KoinAppDeclaration? = null) : KoinApplication {
    return startKoin {
        includes(config)
        modules(networkModule, repositoryModule, domainModule, presentationModule)
    }
}