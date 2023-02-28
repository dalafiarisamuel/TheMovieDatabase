package com.devtamuno.themoviedatabase.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import com.devtamuno.themoviedatabase.R
import com.devtamuno.themoviedatabase.ui.data.MovieDetail
import com.devtamuno.themoviedatabase.ui.screen.state.MovieDetailState
import com.devtamuno.themoviedatabase.ui.tools.getString
import com.devtamuno.themoviedatabase.ui.tools.setTestContent
import org.junit.Rule
import org.junit.Test

class MovieDetailComponentKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val successMovieState = MovieDetailState(
        isLoading = false,
        movie = MovieDetail(
            id = 1234,
            adult = false,
            backdropPath = "https://fakeurl",
            budget = 100_00,
            originalTitle = "Finding Nemo",
            overview = "Kara, devastated by the loss of Krypton, struggles to adjust to her new life on Earth. Her cousin, " +
                    "Superman, mentors her and suggests she leave their space-time to attend the Legion Academy in the 31st century, " +
                    "where she makes new friends and a new enemy: Brainiac 5. Meanwhile, she " +
                    "must contend with a mysterious group called the Dark Circle as it searches for a powerful weapon held in the Academy’s vault.",
            popularity = 8.9,
            posterPath = "https://fakeurl",
            releaseDate = "12-12-2023",
            revenue = 200.0,
            runtime = 123,
            status = "completed",
            tagline = "unknown tag",
            title = "Finding Nemo",
            voteAverage = 5.0,
            voteCount = 200
        )
    )

    private val loadingState = MovieDetailState(
        isLoading = true,
        movie = null,
        error = null
    )

    private val errorState = MovieDetailState(
        isLoading = false,
        movie = null,
        error = Throwable("This is an error text")
    )

    @Test
    fun should_display_loading_view_when_state_is_loading(): Unit = with(composeTestRule) {

        setTestContent {
            MovieDetailComponent(modifier = Modifier.fillMaxSize(), state = loadingState)
        }

        onNodeWithTag("back_icon").assertIsDisplayed()
        onNodeWithTag("loading_view", useUnmergedTree = true).assertIsDisplayed()
        onNodeWithTag("error_view", useUnmergedTree = true).assertDoesNotExist()
        onNodeWithTag("back_icon", useUnmergedTree = true).assertIsDisplayed()


    }

    @Test
    fun should_display_correct_movie_information_in_success_state(): Unit = with(composeTestRule) {

        setTestContent {
            MovieDetailComponent(modifier = Modifier.fillMaxSize(), state = successMovieState)
        }

        onNodeWithText(successMovieState.movie!!.title).assertIsDisplayed()
        onNodeWithText(successMovieState.movie!!.overview).assertIsDisplayed()
        onNodeWithTag("back_icon").assertIsDisplayed()
        onNodeWithTag("loading_view", useUnmergedTree = true).assertDoesNotExist()
        onNodeWithTag("error_view", useUnmergedTree = true).assertDoesNotExist()

        onNodeWithText(successMovieState.movie!!.releaseDate)
            .performScrollTo()
            .assertIsDisplayed()
        onNodeWithText(successMovieState.movie!!.voteCount.toString())
            .performScrollTo()
            .assertIsDisplayed()
        onNodeWithText(
            getString(R.string.duration_minutes, successMovieState.movie!!.runtime.toString())
        ).performScrollTo()
            .assertIsDisplayed()

        onNodeWithText(successMovieState.movie!!.voteAverage.toString()).performScrollTo()
            .assertIsDisplayed()

    }


    @Test
    fun should_display_error_view_when_movie_detail_throws_an_exception(): Unit =
        with(composeTestRule) {

            setTestContent {
                MovieDetailComponent(modifier = Modifier.fillMaxSize(), state = errorState)
            }

            onNodeWithTag("back_icon").assertIsDisplayed()
            onNodeWithTag("loading_view", useUnmergedTree = true).assertDoesNotExist()
            onNodeWithTag("error_view", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithText(errorState.error!!.message!!).assertIsDisplayed()
        }


}