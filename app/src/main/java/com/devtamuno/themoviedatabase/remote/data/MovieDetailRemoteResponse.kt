package com.devtamuno.themoviedatabase.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailRemoteResponse(

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("budget")
    val budget: Int,

    @SerialName("genres")
    val genres: List<GenreRemote>,

    @SerialName("homepage")
    val homepage: String?,

    @SerialName("id")
    val id: Int,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("popularity")
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("release_date")
    val releaseDate: String?,

    @SerialName("revenue")
    val revenue: Double,

    @SerialName("runtime")
    val runtime: Int?,

    @SerialName("status")
    val status: String,

    @SerialName("tagline")
    val tagline: String,

    @SerialName("title")
    val title: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class GenreRemote(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String?
)

