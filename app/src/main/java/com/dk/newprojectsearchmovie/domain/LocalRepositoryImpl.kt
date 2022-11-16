package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.model.imdb.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdb.getMovieTop250ListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail

class LocalRepositoryImpl : Repository {
    override fun getMovieList(movieListType: MovieListType, callback: Any) {
        when (movieListType) {
            MovieListType.TOP250 -> (callback as MovieListCallback).onResponse(
                    getMovieTop250ListFromLocalStorage()
                )

            MovieListType.POPULAR -> (callback as MovieListCallback).onResponse(
                getMoviePopularListFromLocalStorage()
            )
        }
    }

    override fun getMovieDetail(movie: Movie, callback: Any) {

        (callback as MovieDetailCallback).onResponse(
                ImdbMovieDetail(
                    title = movie.title,
                    year = movie.year,
                    imDbRating = movie.imDbRating,
                    image = movie.image
                )
            )
    }
}