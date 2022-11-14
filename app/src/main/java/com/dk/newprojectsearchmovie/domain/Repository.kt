package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail
import retrofit2.Callback

interface Repository {
    fun getMovieList(movieListType: MovieListType, callback: Callback<ImdbMovieList>)
    fun getMovieDetail(movie: Movie, callback: Callback<ImdbMovieDetail>)
}