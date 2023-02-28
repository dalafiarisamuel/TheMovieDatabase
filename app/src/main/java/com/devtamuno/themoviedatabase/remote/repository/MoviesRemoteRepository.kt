package com.devtamuno.themoviedatabase.remote.repository

import com.devtamuno.themoviedatabase.remote.data.MovieDetailRemoteResponse
import com.devtamuno.themoviedatabase.remote.data.MoviesRemoteResponse

interface MoviesRemoteRepository {

    suspend fun getPopularMovies(pageNumber: Int): MoviesRemoteResponse

    suspend fun getSelectedMovieDetail(movieId: Int): Resource<MovieDetailRemoteResponse>
}