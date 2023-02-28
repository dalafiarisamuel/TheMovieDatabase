package com.devtamuno.themoviedatabase.ui.data


data class MovieDetail(

    val id: Int,

    val adult: Boolean,

    val backdropPath: String,

    val budget: Int,

    val originalTitle: String,

    val overview: String,

    val popularity: Double,

    val posterPath: String,

    val releaseDate: String,

    val revenue: Double,

    val runtime: Int,

    val status: String,

    val tagline: String,

    val title: String,

    val voteAverage: Double,

    val voteCount: Int
)

