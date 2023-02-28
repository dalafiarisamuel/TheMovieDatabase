package com.devtamuno.themoviedatabase.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.devtamuno.themoviedatabase.R
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.theme.TheMovieDatabaseTheme
import com.devtamuno.themoviedatabase.ui.theme.appWhite
import kotlinx.coroutines.flow.Flow

@Composable
fun PopularMoviesComponent(
    modifier: Modifier,
    movies: Flow<PagingData<Movie>>,
    onMovieClicked: (Movie?) -> Unit
) {

    val listScrollState = rememberLazyGridState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = TheMovieDatabaseTheme.dimens.standard50,
                end = TheMovieDatabaseTheme.dimens.standard50
            )
    ) {

        Spacer(modifier = Modifier.padding(top = TheMovieDatabaseTheme.dimens.standard125))

        Text(
            text = stringResource(id = R.string.app_name),
            color = appWhite,
            fontSize = 22.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 5.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        MoviesList(
            modifier = Modifier
                .padding(top = TheMovieDatabaseTheme.dimens.standard100),
            movies = movies.collectAsLazyPagingItems(),
            lazyListState = listScrollState,
            onMovieClicked = onMovieClicked,
        )
    }

}