package com.devtamuno.themoviedatabase.ui.data


data class Movie(

    val id: Int,

    val firstAirDate: String,

    val name: String,

    val originalTitle: String,

    val originalLanguage: String,

    val overview: String,

    val posterPath: String,

    val voteAverage: Double,

    val voteCount: Int
) {
    fun isRatingUpto8() = voteAverage >= 8.0
}
