package com.test.movieapplication.network.api

import com.test.movieapplication.network.model.MainModel
import com.test.movieapplication.utils.other.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {

    @GET("movie/popular")
    suspend fun getAllFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: Language = Language.RU,
        @Query("page") page: Int = 1
    ) : Response<MainModel>

}