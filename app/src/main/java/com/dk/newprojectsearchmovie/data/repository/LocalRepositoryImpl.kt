package com.dk.newprojectsearchmovie.data.repository

import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.domain.Repository
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.model.imdb.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdb.getMovieTop250ListFromLocalStorage

class LocalRepositoryImpl : Repository {

    override fun getMovieList(movieListType: MovieListType): ImdbMovieList {
        return when (movieListType) {
            MovieListType.TOP250 -> getMovieTop250ListFromLocalStorage()

            MovieListType.POPULAR ->
                getMoviePopularListFromLocalStorage()

        }
    }

    override fun getMovieDetail(movie: Movie): ImdbMovieDetail {
       return ImdbMovieDetail(
            title = movie.title,
            year = movie.year,
            imDbRating = movie.imDbRating,
            image = movie.image
        )
    }
}