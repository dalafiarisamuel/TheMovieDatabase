package com.devtamuno.themoviedatabase.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesRemoteResponse(
    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val movies: List<MovieRemote>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)
