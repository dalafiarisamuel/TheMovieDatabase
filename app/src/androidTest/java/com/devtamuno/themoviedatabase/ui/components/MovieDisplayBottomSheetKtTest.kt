package com.devtamuno.themoviedatabase.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.devtamuno.themoviedatabase.R
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.tools.getString
import com.devtamuno.themoviedatabase.ui.tools.setTestContent
import org.junit.Rule
import org.junit.Test

class MovieDisplayBottomSheetKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val movie = Movie(
        id = 1234,
        originalTitle = "Finding Nemo",
        overview = "Kara, devastated by the loss ",
        posterPath = "https://fakeurl",
        firstAirDate = "12-12-2023",
        originalLanguage = "en",
        name = "Finding Nemo",
        voteAverage = 8.0,
        voteCount = 200
    )

    @Test
    fun should_display_correct_movie_information(): Unit = with(composeTestRule) {

        setTestContent {
            MovieDisplayBottomSheet(
                modifier = Modifier.fillMaxSize(),
                movie = movie
            )
        }

        onNodeWithText(movie.name).assertIsDisplayed()
        onNodeWithText(movie.overview).assertIsDisplayed()
        onNodeWithText(getString(R.string.overview)).assertIsDisplayed()
        onNodeWithTag("rating").assertIsDisplayed()

    }

    @Test
    fun should_not_display_rating_view_when_its_less_than_expected_average(): Unit =
        with(composeTestRule) {

            setTestContent {
                MovieDisplayBottomSheet(
                    modifier = Modifier.fillMaxSize(),
                    movie = movie.copy(voteAverage = 5.0)
                )
            }

            onNodeWithText(movie.name).assertIsDisplayed()
            onNodeWithText(movie.overview).assertIsDisplayed()
            onNodeWithTag("rating").assertDoesNotExist()

        }


}