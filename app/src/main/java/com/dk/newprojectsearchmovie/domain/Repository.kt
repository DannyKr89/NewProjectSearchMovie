package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdb.Movie

interface Repository {
    fun getMovieList(movieListType: MovieListType, callback: Any)
    fun getMovieDetail(movie: Movie, callback: Any)
}