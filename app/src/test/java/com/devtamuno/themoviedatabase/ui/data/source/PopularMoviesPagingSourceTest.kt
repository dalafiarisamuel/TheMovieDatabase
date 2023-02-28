package com.devtamuno.themoviedatabase.ui.data.source

import androidx.paging.PagingSource
import com.devtamuno.themoviedatabase.remote.data.MovieRemote
import com.devtamuno.themoviedatabase.remote.data.MoviesRemoteResponse
import com.devtamuno.themoviedatabase.remote.repository.MoviesRemoteRepository
import com.devtamuno.themoviedatabase.tools.CoroutineTestRule
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.data.mapper.MovieMapper
import com.devtamuno.themoviedatabase.ui.data.mapper.MoviesMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesPagingSourceTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var movieRepository: MoviesRemoteRepository

    private lateinit var movieMapper: MovieMapper

    private lateinit var moviesMapper: MoviesMapper


    private lateinit var popularMoviesPagingSource: PopularMoviesPagingSource

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

    private val loadParams = PagingSource.LoadParams.Refresh(1, 1, true)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieMapper = MovieMapper()
        moviesMapper = MoviesMapper(movieMapper)

        popularMoviesPagingSource =
            PopularMoviesPagingSource(repository = movieRepository, moviesMapper = moviesMapper)
    }


    @Test
    fun `when getPopularMovies repository returns a list of movies`() = runTest {
        coEvery { movieRepository.getPopularMovies(any()) } returns moviesRemoteResponse

        val loadedResult = popularMoviesPagingSource.load(loadParams)

        assertThat(loadedResult).isEqualTo(
            PagingSource.LoadResult.Page(
                data = moviesMapper.mapToUi(movieRepository.getPopularMovies(1)).movies,
                prevKey = null,
                nextKey = null
            )
        )

    }

    @Test
    fun `when exception is thrown in getPopularMovies repository`() = runTest {
        val ioException = IOException("this is an io exception")
        coEvery { movieRepository.getPopularMovies(any()) } throws ioException

        val errorLoadResult = popularMoviesPagingSource.load(loadParams)

        assertThat(errorLoadResult).isEqualTo(
            PagingSource.LoadResult.Error<Int, Movie>(ioException)
        )

        coVerify { movieRepository.getPopularMovies(eq(1)) }
    }
}