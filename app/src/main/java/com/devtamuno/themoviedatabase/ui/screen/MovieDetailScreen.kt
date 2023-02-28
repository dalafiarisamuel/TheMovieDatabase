package com.devtamuno.themoviedatabase.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devtamuno.themoviedatabase.ui.components.MovieDetailComponent
import com.devtamuno.themoviedatabase.ui.theme.LocalNavController
import com.devtamuno.themoviedatabase.ui.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen() {

    val viewModel = hiltViewModel<MovieDetailViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val navController = LocalNavController.current

    MovieDetailComponent(modifier = Modifier.fillMaxSize(), state = state) {
        navController.popBackStack()
    }

}