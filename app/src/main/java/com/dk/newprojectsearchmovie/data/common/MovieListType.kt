package com.dk.newprojectsearchmovie.data.common

enum class MovieListType() {
    TOP250, POPULAR
}

fun getMovieListType(movieListType: MovieListType): String {
    return when (movieListType) {
        MovieListType.TOP250 -> {
            "Top250Movies"
        }
        MovieListType.POPULAR -> {
            "MostPopularMovies"
        }
    }
}