package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie

class RemoteRepositoryImpl: Repository {
    override fun getMovieList(movieListType: MovieListType): List<Movie> {
        return RequestApi.requestMovieList(movieListType)
    }
}