package com.test.movieapplication.repository

import com.test.movieapplication.model.MainModel
import com.test.movieapplication.network.api.FilmApi
import com.test.movieapplication.utils.Language
import retrofit2.Response

class FilmsRepository(
    private val filmsApi: FilmApi
) {

    suspend fun getAllFilms(
        apiKey: String,
        language: Language,
        page: Int
    ) : Response<MainModel> {
        return filmsApi.getAllFilms(
            apiKey = apiKey,
            language = language,
            page = page
        )
    }

}