package com.mdshahsamir.starwars.domain.usecases

import com.mdshahsamir.starwars.data.repository.FilmRepository

class GetFilmsUseCase(
    private val filmRepository: FilmRepository
) {
    suspend operator fun invoke() = filmRepository.getAllFilms().sortedBy { it.title }
}