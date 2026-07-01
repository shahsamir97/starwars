package com.mdshahsamir.starwars.di

import com.apollographql.apollo.ApolloClient
import com.mdshahsamir.starwars.data.remote.StarWarApiImpl
import com.mdshahsamir.starwars.data.remote.StarWarsApi
import com.mdshahsamir.starwars.data.repository.FilmRepository
import com.mdshahsamir.starwars.data.repository.FilmRepositoryImpl
import com.mdshahsamir.starwars.domain.usecases.GetFilmsUseCase
import com.mdshahsamir.starwars.presentation.FilmListViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


val networkModule = module {
    single { ApolloClient.Builder().serverUrl("https://swapi-graphql.netlify.app/graphql").build() }
    factory<StarWarsApi> { StarWarApiImpl(get()) }
}

val repositoryModule = module {
    factory<FilmRepository> { FilmRepositoryImpl(get()) }
}

val domainModule = module {
    factory { GetFilmsUseCase(get()) }
}

val presentationModule = module {
    factory { FilmListViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(networkModule, repositoryModule, domainModule, presentationModule)
}