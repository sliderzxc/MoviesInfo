package com.test.movieapplication.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.test.movieapplication.network.api.FilmApi
import com.test.movieapplication.network.paging.EverythingFilmsPageSource
import com.test.movieapplication.utils.Constants
import javax.inject.Inject

class FilmsRepositoryPaging @Inject constructor(private val filmApi: FilmApi) {

    fun getFilms() = Pager(
        config = PagingConfig(Constants.MAX_PAGE_SIZE),
        pagingSourceFactory = { EverythingFilmsPageSource(filmApi) }
    ).liveData

}