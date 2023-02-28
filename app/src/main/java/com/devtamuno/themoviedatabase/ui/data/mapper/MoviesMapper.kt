package com.devtamuno.themoviedatabase.ui.data.mapper

import com.devtamuno.themoviedatabase.remote.data.MoviesRemoteResponse
import com.devtamuno.themoviedatabase.ui.data.Movies
import javax.inject.Inject

class MoviesMapper @Inject constructor(private val movieMapper: MovieMapper) :
    UiMapper<MoviesRemoteResponse, Movies> {
    override fun mapToUi(input: MoviesRemoteResponse): Movies {
        return with(input) {
            Movies(
                page = page,

                movies = movies.map(movieMapper::mapToUi),

                totalPages = totalPages,

                totalResults = totalResults
            )
        }
    }
}