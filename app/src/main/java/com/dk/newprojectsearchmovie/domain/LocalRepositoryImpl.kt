package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.getMovieTop250ListFromLocalStorage

class LocalRepositoryImpl: Repository {
    override fun getMovieList(movieListType: MovieListType): List<Movie> {
        return when (movieListType) {
            MovieListType.TOP250 -> getMovieTop250ListFromLocalStorage()
            MovieListType.POPULAR -> getMoviePopularListFromLocalStorage()
        }
    }
}