package com.devtamuno.themoviedatabase.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.theme.TheMovieDatabaseTheme

@Composable
fun MoviesList(
    modifier: Modifier,
    movies: LazyPagingItems<Movie>,
    lazyListState: LazyGridState,
    onMovieClicked: (Movie?) -> Unit
) {

    val isListEmpty by remember { derivedStateOf { movies.itemCount <= 0 } }

    if (movies.loadState.refresh is LoadState.Loading) {
        LoadingView(modifier = Modifier
            .fillMaxSize()
            .testTag("loading_indicator"))
    } else {
        Crossfade(targetState = isListEmpty) {
            if (it) {
                EmptyListStateComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("empty_list_state_indicator")
                )
            } else {
                MovieListGridComponent(
                    modifier = modifier,
                    movieList = movies,
                    lazyListState = lazyListState,
                    onMovieClicked = onMovieClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovieListGridComponent(
    modifier: Modifier,
    movieList: LazyPagingItems<Movie>,
    lazyListState: LazyGridState,
    onMovieClicked: (Movie?) -> Unit,
) {
    LazyVerticalGrid(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(TheMovieDatabaseTheme.dimens.standard50),
        horizontalArrangement = Arrangement.spacedBy(TheMovieDatabaseTheme.dimens.standard50),
        modifier = Modifier
            .testTag("moviesLazyList")
            .then(modifier),
        columns = GridCells.Fixed(2),
        content = {
            items(movieList) { movie ->
                MovieComponent(
                    modifier = Modifier.animateItemPlacement(),
                    movie = movie,
                    onMovieClicked = onMovieClicked
                )
            }
        }
    )
}

private fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}