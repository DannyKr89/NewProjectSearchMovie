package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.imdb.imdbMovie.ImdbMovieDetail

interface Repository {
    fun getMovieList(movieListType: MovieListType): List<Movie>
    fun getMovieDetail(movie: Movie): ImdbMovieDetail
}