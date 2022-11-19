package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.model.imdb.Movie

interface Repository<T,E> {
    fun getMovieList(movieListType: MovieListType, callbackList: T)
    fun getMovieDetail(movie: Movie, callbackDetail: E)
}
