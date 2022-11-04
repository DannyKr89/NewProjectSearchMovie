package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie

interface Repository {
    fun getMovieTop250List(): List<Movie>
    fun getMoviePopularList(): List<Movie>
}