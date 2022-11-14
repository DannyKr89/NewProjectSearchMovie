package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail
import retrofit2.Callback

class RemoteRepositoryImpl: Repository {
    override fun getMovieList(movieListType: MovieListType, callback: Callback<ImdbMovieList>) {
        return RequestAPI.create().getMovieList(getMovieListType(movieListType)).enqueue(callback)
    }

    override fun getMovieDetail(movie: Movie, callback: Callback<ImdbMovieDetail>) {
        return RequestAPI.create().getMovieDetail(movie.id.toString()).enqueue(callback)
    }
}