package com.devtamuno.themoviedatabase.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.PagingSource
import coil.Coil
import com.devtamuno.themoviedatabase.R
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.tools.FakeImageLoader
import com.devtamuno.themoviedatabase.ui.tools.getString
import com.devtamuno.themoviedatabase.ui.tools.setTestContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PopularMoviesComponentKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    private val result = PagingSource.LoadResult.Page(
        data = listOf(
            Movie(
                id = 1,
                originalTitle = "Finding Nemo",
                overview = "this is a random overview",
                posterPath = "https://image.tmdb.org/t/p/w342https://fakeurl",
                firstAirDate = "12-12-2023",
                originalLanguage = "en",
                name = "Finding Nemo",
                voteAverage = 5.0,
                voteCount = 7
            ),
            Movie(
                id = 2,
                originalTitle = "Finding Nemo 2",
                overview = "this is a random overview",
                posterPath = "https://image.tmdb.org/t/p/w342https://fakeurl",
                firstAirDate = "12-12-2024",
                originalLanguage = "en",
                name = "Finding Nemo",
                voteAverage = 8.0,
                voteCount = 10
            )
        ),
        prevKey = 1,
        nextKey = 1
    )

    private val data = flowOf(PagingData.from(result.data))
    private val emptyData: Flow<PagingData<Movie>> = flowOf(PagingData.empty())

    @Before
    fun start() {
        Coil.setImageLoader(FakeImageLoader())
    }


    @Test
    fun display_empty_state_when_list_is_empty(): Unit = with(composeTestRule) {

        setTestContent {
            PopularMoviesComponent(
                modifier = Modifier.fillMaxSize(),
                movies = emptyData,
                onMovieClicked = {}
            )
        }

        onNodeWithText(getString(R.string.app_name)).assertIsDisplayed()
        onNodeWithTag("empty_list_state_indicator").assertIsDisplayed()
        onNodeWithTag("moviesLazyList").assertDoesNotExist()
        onNodeWithTag("loading_indicator").assertDoesNotExist()
    }


    @Test
    fun display_movie_list_from_data(): Unit = with(composeTestRule) {

        setTestContent {
            PopularMoviesComponent(
                modifier = Modifier.fillMaxSize(),
                movies = data,
                onMovieClicked = {}
            )
        }

        onNodeWithText(getString(R.string.app_name)).assertIsDisplayed()
        onNodeWithTag("empty_list_state_indicator").assertDoesNotExist()
        onNodeWithTag("loading_indicator").assertDoesNotExist()
        onNodeWithTag("moviesLazyList")
            .onChildren()
            .assertCountEquals(2)
    }


}