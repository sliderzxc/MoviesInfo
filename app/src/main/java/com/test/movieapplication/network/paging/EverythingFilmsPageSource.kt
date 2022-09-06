package com.test.movieapplication.network.paging

import com.test.movieapplication.model.*
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.movieapplication.network.api.FilmApi
import com.test.movieapplication.utils.Constants.API_KEY
import com.test.movieapplication.utils.Language
import java.lang.Exception

class EverythingFilmsPageSource(
    private val filmApi: FilmApi
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val page = params.key ?: 1
            val repoResponse = filmApi.getAllFilms(
                apiKey = API_KEY,
                language = Language.RU,
                page = page
            )
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