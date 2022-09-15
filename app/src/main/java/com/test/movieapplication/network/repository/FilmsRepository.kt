package com.test.movieapplication.network.repository

import com.test.movieapplication.network.api.FilmApi
import com.test.movieapplication.network.model.MainModel
import com.test.movieapplication.utils.other.Constants.API_KEY
import com.test.movieapplication.utils.other.Language
import retrofit2.Response

class FilmsRepository(
    private val filmsApi: FilmApi
) {

    suspend fun getAllFilms(
        apiKey: String = API_KEY,
        language: Language = Language.RU,
        page: Int
    ) : Response<MainModel> {
        return filmsApi.getAllFilms(
            apiKey = apiKey,
            language = language,
            page = page
        )
    }

}