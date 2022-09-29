package com.test.movieapplication.utils.mapper

import com.test.movieapplication.database.model.ResultDatabaseModel
import com.test.movieapplication.network.model.Result

fun Result.toResultDatabaseModel() : ResultDatabaseModel {
    return ResultDatabaseModel(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        id = this.id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

fun ResultDatabaseModel.toResult() : Result {
    return Result(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        id = this.id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}