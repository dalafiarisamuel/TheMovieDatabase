package com.devtamuno.themoviedatabase.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.devtamuno.themoviedatabase.remote.data.MovieDetailRemoteResponse
import com.devtamuno.themoviedatabase.remote.repository.MoviesRemoteRepository
import com.devtamuno.themoviedatabase.remote.repository.Resource
import com.devtamuno.themoviedatabase.tools.CoroutineTestRule
import com.devtamuno.themoviedatabase.tools.test
import com.devtamuno.themoviedatabase.ui.data.mapper.MovieDetailMapper
import com.devtamuno.themoviedatabase.ui.navigation.ARG_MOVIE_ID
import com.devtamuno.themoviedatabase.ui.screen.state.MovieDetailState
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var movieRepository: MoviesRemoteRepository

    private var movieDetailMapper: MovieDetailMapper = MovieDetailMapper()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    private val intentMovieId = 123456

    private lateinit var viewModel: MovieDetailViewModel

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

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { savedStateHandle.get<Int>(ARG_MOVIE_ID) } returns intentMovieId
    }

    @Test
    fun `when getSelectedMovieDetail returns success state`() = runTest {

        coEvery { movieRepository.getSelectedMovieDetail(intentMovieId) } returns Resource.Success(
            movieDetailRemoteResponse
        )
        initViewModel()

        val stateValues = viewModel.uiState.test()
        val movieDetail = movieDetailMapper.mapToUi(movieDetailRemoteResponse)
        val lastResult = stateValues.last().movie

        coVerify(exactly = 1) {
            movieRepository.getSelectedMovieDetail(intentMovieId)
        }

        assertThat(movieRepository.getSelectedMovieDetail(intentMovieId))
            .isInstanceOf(Resource.Success::class.java)

        assertThat(lastResult).isEqualTo(movieDetail)
    }

    @Test
    fun `when getSelectedMovieDetail returns failure state`() = runTest {

        val error = Throwable("This is an error message")

        coEvery { movieRepository.getSelectedMovieDetail(intentMovieId) } returns Resource.Failure(
            error
        )

        initViewModel()

        val stateValues = viewModel.uiState.test()
        val lastResult = stateValues.last()
        val expectedState = MovieDetailState(movie = null, isLoading = false, error = error)

        coVerify(exactly = 1) {
            movieRepository.getSelectedMovieDetail(intentMovieId)
        }

        assertThat(movieRepository.getSelectedMovieDetail(intentMovieId))
            .isInstanceOf(Resource.Failure::class.java)

        assertThat(lastResult).isEqualTo(expectedState)
    }

    private fun initViewModel() {
        viewModel = MovieDetailViewModel(
            repository = movieRepository,
            movieDetailMapper = movieDetailMapper,
            savedStateHandle = savedStateHandle
        )
    }
}