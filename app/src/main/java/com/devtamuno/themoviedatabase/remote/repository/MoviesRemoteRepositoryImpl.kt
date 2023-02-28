package com.devtamuno.themoviedatabase.remote.repository

import com.devtamuno.themoviedatabase.remote.data.MovieDetailRemoteResponse
import com.devtamuno.themoviedatabase.remote.data.MoviesRemoteResponse
import com.devtamuno.themoviedatabase.remote.service.MovieService

class MoviesRemoteRepositoryImpl(private val api: MovieService) : MoviesRemoteRepository {

    override suspend fun getPopularMovies(pageNumber: Int): MoviesRemoteResponse {
        return api.getPopularMovies(pageNumber)
    }

    override suspend fun getSelectedMovieDetail(movieId: Int): Resource<MovieDetailRemoteResponse> {
        return resourceHelper { api.getSelectedMovieDetail(movieId) }
    }
}