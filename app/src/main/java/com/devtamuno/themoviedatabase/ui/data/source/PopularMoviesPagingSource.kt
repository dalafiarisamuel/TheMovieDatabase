package com.devtamuno.themoviedatabase.ui.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devtamuno.themoviedatabase.remote.repository.MoviesRemoteRepository
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.data.mapper.MoviesMapper
import java.io.IOException
import retrofit2.HttpException


class PopularMoviesPagingSource(
    private val repository: MoviesRemoteRepository,
    private val moviesMapper: MoviesMapper,
) : PagingSource<Int, Movie>() {

    companion object {
        private const val startingIndex = 1
        const val defaultPageSize = 20
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int {
        return startingIndex
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: startingIndex

        return try {
            val response = repository.getPopularMovies(page)
            val movies = moviesMapper.mapToUi(response).movies

            LoadResult.Page(
                data = movies,
                prevKey = if (page == startingIndex) null else page.minus(1),
                nextKey = if (page >= response.totalPages) null else response.page.plus(1)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}