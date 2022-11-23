package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import retrofit2.Callback

interface Repository {
    fun getMovieList(movieListType: MovieListType): ImdbMovieList?
    fun getMovieSearchList(
        titleQuery: String, minRating: String, callback: Callback<RequestSearchMovie>
    )

    fun getMovieDetail(movie: Movie): ImdbMovieDetail?
}
