package com.devtamuno.themoviedatabase.ui.screen.state


import com.devtamuno.themoviedatabase.ui.data.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = true,
    val movie: MovieDetail? = null,
    val error: Throwable? = null
)
