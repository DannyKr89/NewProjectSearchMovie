package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import retrofit2.Callback

interface Repository {
    fun getMovieList(movieListType: MovieListType, callback: Callback<ImdbMovieList>)
    fun getMovieDetail(movie: Movie, callback: Callback<ImdbMovieDetail>)
}
