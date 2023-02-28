package com.devtamuno.themoviedatabase.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devtamuno.themoviedatabase.ui.components.MovieDisplayBottomSheet
import com.devtamuno.themoviedatabase.ui.components.PopularMoviesComponent
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.navigation.MovieScreen
import com.devtamuno.themoviedatabase.ui.theme.LocalNavController
import com.devtamuno.themoviedatabase.ui.theme.TheMovieDatabaseTheme
import com.devtamuno.themoviedatabase.ui.viewmodel.PopularMoviesViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopularMoviesScreen() {
    val viewModel = hiltViewModel<PopularMoviesViewModel>()
    val navController = LocalNavController.current
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var selectedMovie by remember { mutableStateOf<Movie?>(null) }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            topStart = TheMovieDatabaseTheme.dimens.standard75,
            topEnd = TheMovieDatabaseTheme.dimens.standard75
        ),
        sheetContent = {
            if (selectedMovie == null) {
                Box(
                    modifier = Modifier
                        .size(1.dp)
                )
                return@ModalBottomSheetLayout
            }

            MovieDisplayBottomSheet(
                movie = selectedMovie!!
            ) {
                coroutineScope.launch {
                    modalSheetState.hide()
                    navController.navigate(MovieScreen.MOVIE_DETAIL.createPath(selectedMovie!!.id))
                }
            }

        }
    ) {
        PopularMoviesComponent(
            modifier = Modifier.fillMaxSize(),
            movies = viewModel.movies,
            onMovieClicked = { movie ->
                selectedMovie = movie
                if (selectedMovie != null) {
                    coroutineScope.launch {
                        if (modalSheetState.isVisible) modalSheetState.hide()
                        else modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                    }
                }
            },
        )
    }

    BackHandler(enabled = modalSheetState.isVisible) {
        coroutineScope.launch {
            modalSheetState.hide()
        }
    }

}