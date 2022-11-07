package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.imdb.imdbMovie.ImdbMovieDetail

class RemoteRepositoryImpl: Repository {
    override fun getMovieList(movieListType: MovieListType): List<Movie> {
        return RequestApi.requestMovieList(movieListType)
    }

    override fun getMovieDetail(movie: Movie): ImdbMovieDetail {
    return RequestApi.requestMovie(movie)
    }
}