package com.devtamuno.themoviedatabase.ui.data.mapper

import com.devtamuno.themoviedatabase.remote.data.MovieRemote
import com.devtamuno.themoviedatabase.remote.data.MoviesRemoteResponse
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.data.Movies
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MoviesMapperTest {

    private lateinit var movieMapper: MovieMapper

    private lateinit var moviesMapper: MoviesMapper

    private val moviesRemoteResponse =
        MoviesRemoteResponse(
            page = 1,
            listOf(
                MovieRemote(
                    1,
                    "12-12-2023",
                    "Finding Nemo",
                    "Finding Nemo",
                    "en",
                    "this is a random overview",
                    "https://fakeurl",
                    5.0,
                    1
                )
            ),
            1,
            1
        )

    private val expected = Movies(
        page = 1,
        totalPages = 1,
        totalResults = 1,
        movies = listOf(
            Movie(
                id = 1,
                originalTitle = "Finding Nemo",
                overview = "this is a random overview",
                posterPath = "https://image.tmdb.org/t/p/w342https://fakeurl",
                firstAirDate = "12-12-2023",
                originalLanguage = "en",
                name = "Finding Nemo",
                voteAverage = 5.0,
                voteCount = 1
            )
        )
    )

    @Test
    fun mapToUi() {

        movieMapper = MovieMapper()
        moviesMapper = MoviesMapper(movieMapper)

        assertThat(expected).isEqualTo(moviesMapper.mapToUi(moviesRemoteResponse))

    }
}