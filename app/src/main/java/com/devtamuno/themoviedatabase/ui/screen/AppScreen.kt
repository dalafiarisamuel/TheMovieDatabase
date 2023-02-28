package com.devtamuno.themoviedatabase.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devtamuno.themoviedatabase.ui.navigation.ARG_MOVIE_ID
import com.devtamuno.themoviedatabase.ui.navigation.MovieScreen
import com.devtamuno.themoviedatabase.ui.theme.LocalNavController

@Composable
fun AppScreen() {

    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = MovieScreen.POPULAR_MOVIES.route) {

        composable(MovieScreen.POPULAR_MOVIES.route) { PopularMoviesScreen() }

        composable(
            route = MovieScreen.MOVIE_DETAIL.route,
            arguments = listOf(navArgument(ARG_MOVIE_ID) { type = NavType.IntType }),
            content = { MovieDetailScreen() }
        )

    }
}