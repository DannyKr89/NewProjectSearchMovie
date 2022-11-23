package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.data.repository.LocalRepositoryImpl
import com.dk.newprojectsearchmovie.data.repository.RemoteRepositoryImpl
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import retrofit2.Callback

class MovieInteractor(private val storage: Boolean) {

    fun getList(movieListType: MovieListType): ImdbMovieList? {
        return if (storage) {
            RemoteRepositoryImpl().getMovieList(movieListType)
        } else {
            LocalRepositoryImpl().getMovieList(movieListType)
        }
    }

    fun getDetail(movie: Movie): ImdbMovieDetail? {
        return if (storage) {
            RemoteRepositoryImpl().getMovieDetail(movie)
        } else {
            LocalRepositoryImpl().getMovieDetail(movie)
        }
    }

    fun getSearchList(titleQuery: String, minRating: String, callback: Callback<RequestSearchMovie>) {
        return RemoteRepositoryImpl().getMovieSearchList(titleQuery,minRating, callback)
    }
}