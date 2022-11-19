package com.dk.newprojectsearchmovie.data.repository

import com.dk.newprojectsearchmovie.data.common.MovieDetailCallback
import com.dk.newprojectsearchmovie.data.common.MovieListCallback
import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.domain.Repository
import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.model.imdb.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdb.getMovieTop250ListFromLocalStorage
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail

class LocalRepositoryImpl : Repository<MovieListCallback, MovieDetailCallback> {

    override fun getMovieList(movieListType: MovieListType, callbackList: MovieListCallback) {
        when (movieListType) {
            MovieListType.TOP250 -> callbackList.onResponse(getMovieTop250ListFromLocalStorage())

            MovieListType.POPULAR -> callbackList.onResponse(
                getMoviePopularListFromLocalStorage()
            )
        }
    }

    override fun getMovieDetail(movie: Movie, callbackDetail: MovieDetailCallback) {

        callbackDetail.onResponse(
            ImdbMovieDetail(
                title = movie.title,
                year = movie.year,
                imDbRating = movie.imDbRating,
                image = movie.image
            )
        )
    }
}