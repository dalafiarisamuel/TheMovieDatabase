package com.devtamuno.themoviedatabase.ui.navigation

const val ARG_MOVIE_ID = "movie_id"

enum class MovieScreen(val route: String) {
    POPULAR_MOVIES("popular_movies"),
    MOVIE_DETAIL("movie/{$ARG_MOVIE_ID}/detail");

    fun createPath(vararg args: Any): String {
        var route = route
        require(args.size == route.argumentCount) {
            "Provided ${args.count()} parameters, was expected ${route.argumentCount} parameters!"
        }
        route.arguments().forEachIndexed { index, matchResult ->
            route = route.replace(matchResult.value, args[index].toString())
        }
        return route
    }
}
