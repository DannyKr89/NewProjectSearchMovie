package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.getMovieTop250ListFromLocalStorage

class LocalRepositoryImpl: Repository {
    override fun getMovieTop250List(): List<Movie> {
        return getMovieTop250ListFromLocalStorage()
    }

    override fun getMoviePopularList(): List<Movie> {
        return getMoviePopularListFromLocalStorage()
    }
}