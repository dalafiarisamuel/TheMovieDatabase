package com.devtamuno.themoviedatabase.ui.data.mapper

import com.devtamuno.themoviedatabase.remote.data.MovieDetailRemoteResponse
import com.devtamuno.themoviedatabase.ui.data.MovieDetail
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MovieDetailMapperTest {


    private val movieDetailRemoteResponse = MovieDetailRemoteResponse(
        adult = false,
        backdropPath = "https://fakeurl",
        budget = 100_000,
        genres = listOf(),
        homepage = "",
        id = 123456,
        originalLanguage = "en",
        originalTitle = "Finding Nemo",
        overview = "random overview",
        popularity = 6.7,
        posterPath = "https://fakeurl",
        releaseDate = "12-12-2023",
        revenue = 2023.0,
        runtime = 125,
        status = "released",
        tagline = "",
        title = "Finding Nemo",
        voteAverage = 8.0,
        voteCount = 100
    )

    private val expectedResult = MovieDetail(
        id = 123456,
        adult = false,
        backdropPath = "https://image.tmdb.org/t/p/originalhttps://fakeurl",
        budget = 100_000,
        originalTitle = "Finding Nemo",
        overview = "random overview",
        popularity = 6.7,
        posterPath = "https://image.tmdb.org/t/p/w342https://fakeurl",
        releaseDate = "12-12-2023",
        revenue = 2023.0,
        runtime = 125,
        status = "released",
        tagline = "",
        title = "Finding Nemo",
        voteAverage = 8.0,
        voteCount = 100
    )

    private val mapper = MovieDetailMapper()

    @Test
    fun mapToUi() {

        assertThat(expectedResult).isEqualTo(mapper.mapToUi(movieDetailRemoteResponse))

    }

}