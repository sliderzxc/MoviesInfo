package com.test.movieapplication.paging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.movieapplication.network.model.Result
import com.test.movieapplication.network.repository.FilmsRepository

class EverythingFilmsPageSource(
    private val filmsRepository: FilmsRepository
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val page = params.key ?: 1
            val repoResponse = filmsRepository.getAllFilms(page = page)
            if (repoResponse.isSuccessful) {
                val films = repoResponse.body()?.results
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (films?.isNotEmpty() == true) page + 1 else null
                return LoadResult.Page(films!!, prevKey, nextKey)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
        return LoadResult.Invalid()
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? = null
}