package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.model.imdb.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdb.getMovieTop250ListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail

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