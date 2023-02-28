package com.devtamuno.themoviedatabase.ui.data.mapper

import com.devtamuno.themoviedatabase.remote.data.MovieRemote
import com.devtamuno.themoviedatabase.ui.data.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() : UiMapper<MovieRemote, Movie> {
    override fun mapToUi(input: MovieRemote): Movie {
        return with(input) {
            Movie(
                id = id,

                firstAirDate = firstAirDate.orEmpty(),

                name = name,

                originalTitle = originalTitle,

                originalLanguage = originalLanguage,

                overview = overview,

                posterPath = "https://image.tmdb.org/t/p/w342${posterPath.orEmpty()}",

                voteAverage = voteAverage,

                voteCount = voteCount
            )
        }
    }
}