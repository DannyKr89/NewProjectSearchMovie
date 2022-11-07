package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.getMovieTop250ListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdb.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.model.imdb.imdbMovie.getTestMovie

class LocalRepositoryImpl: Repository {
    override fun getMovieList(movieListType: MovieListType): List<Movie> {
        return when (movieListType) {
            MovieListType.TOP250 -> getMovieTop250ListFromLocalStorage()
            MovieListType.POPULAR -> getMoviePopularListFromLocalStorage()
        }
    }

    override fun getMovieDetail(movie: Movie): ImdbMovieDetail {
//        return ImdbMovieDetail(
//            title = movie.title,
//            year = movie.year,
//            imDbRating = movie.rating,
//            image = movie.poster,
//            posterLocal = movie.posterLocal
//        )
        return getTestMovie()
    }
}