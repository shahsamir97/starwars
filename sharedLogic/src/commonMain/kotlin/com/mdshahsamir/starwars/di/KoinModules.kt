package com.mdshahsamir.starwars.di

import com.mdshahsamir.starwars.data.remote.StarWarsApi
import com.mdshahsamir.starwars.data.repository.FilmRepository
import com.mdshahsamir.starwars.data.repository.FilmRepositoryImpl
import com.mdshahsamir.starwars.domain.usecases.GetFilmsUseCase
import com.mdshahsamir.starwars.network.GraphQLClient
import com.mdshahsamir.starwars.presentation.FilmListViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val dataModule = module {
    single { GraphQLClient() }
    single { StarWarsApi(get()) }
    single<FilmRepository> { FilmRepositoryImpl(get()) }
}

val domainModule = module {
    factory { GetFilmsUseCase(get()) }
}

val presentationModule = module {
    factory { FilmListViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(dataModule, domainModule, presentationModule)
}