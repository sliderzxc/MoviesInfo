package com.test.movieapplication.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.test.movieapplication.network.repository.FilmsRepository
import com.test.movieapplication.paging.source.EverythingFilmsPageSource
import com.test.movieapplication.utils.other.MainConstants.MAX_PAGE_SIZE
import javax.inject.Inject

class FilmsRepositoryPaging @Inject constructor(
    private val filmsRepository: FilmsRepository
) {

    fun getFilms() = Pager(
        config = PagingConfig(pageSize = MAX_PAGE_SIZE),
        pagingSourceFactory = { EverythingFilmsPageSource(filmsRepository = filmsRepository) }
    ).liveData

}