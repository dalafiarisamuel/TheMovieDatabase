package com.devtamuno.themoviedatabase.ui.data.mapper

import com.devtamuno.themoviedatabase.remote.data.MovieDetailRemoteResponse
import com.devtamuno.themoviedatabase.ui.data.MovieDetail
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : UiMapper<MovieDetailRemoteResponse, MovieDetail> {

    override fun mapToUi(input: MovieDetailRemoteResponse): MovieDetail {
        return with(input) {

            MovieDetail(
                id = id,

                adult = adult,

                backdropPath = "https://image.tmdb.org/t/p/original${backdropPath.orEmpty()}",

                budget = budget,

                originalTitle = originalTitle,

                overview = overview,

                popularity = popularity,

                posterPath = "https://image.tmdb.org/t/p/w342$posterPath",

                releaseDate = releaseDate.orEmpty(),

                revenue = revenue,

                runtime = runtime ?: 0,

                status = status,

                tagline = tagline,

                title = title,

                voteAverage = voteAverage,

                voteCount = voteCount
            )
        }
    }
}