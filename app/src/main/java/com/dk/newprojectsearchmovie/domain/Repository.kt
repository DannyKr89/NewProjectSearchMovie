package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie

interface Repository {
    fun getMovieList(movieListType: MovieListType): List<Movie>
}