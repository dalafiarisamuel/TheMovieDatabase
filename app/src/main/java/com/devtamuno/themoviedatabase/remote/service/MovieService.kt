package com.devtamuno.themoviedatabase.remote.service

import com.devtamuno.themoviedatabase.remote.data.MovieDetailRemoteResponse
import com.devtamuno.themoviedatabase.remote.data.MoviesRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") pageNumber: Int
    ): MoviesRemoteResponse

    @GET("movie/{movie_id}")
    suspend fun getSelectedMovieDetail(
        @Path("movie_id") movieId: Int
    ): MovieDetailRemoteResponse


}
