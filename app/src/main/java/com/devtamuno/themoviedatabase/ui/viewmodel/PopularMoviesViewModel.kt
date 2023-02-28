package com.devtamuno.themoviedatabase.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.devtamuno.themoviedatabase.remote.repository.MoviesRemoteRepository
import com.devtamuno.themoviedatabase.ui.data.mapper.MoviesMapper
import com.devtamuno.themoviedatabase.ui.data.source.PopularMoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val repository: MoviesRemoteRepository,
    private val moviesMapper: MoviesMapper,
) : ViewModel() {

    @VisibleForTesting
    lateinit var popularMoviesSource: PopularMoviesPagingSource
        private set

    @VisibleForTesting
    fun iniPopularMoviesPagingSource() = PopularMoviesPagingSource(
        repository = repository,
        moviesMapper = moviesMapper,
    ).also {
        popularMoviesSource = it
    }

    private fun getPopularMovies() = Pager(
        config = PagingConfig(
            pageSize = PopularMoviesPagingSource.defaultPageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { iniPopularMoviesPagingSource() }
    ).flow


    val movies = getPopularMovies().cachedIn(viewModelScope)
}