package com.test.movieapplication.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.test.movieapplication.repository.FilmsRepository
import com.test.movieapplication.utils.Constants.MAX_PAGE_SIZE
import javax.inject.Inject

class FilmsRepositoryPaging @Inject constructor(
    private val filmsRepository: FilmsRepository
) {

    fun getFilms() = Pager(
        config = PagingConfig(pageSize = MAX_PAGE_SIZE),
        pagingSourceFactory = { EverythingFilmsPageSource(filmsRepository = filmsRepository) }
    ).liveData

}