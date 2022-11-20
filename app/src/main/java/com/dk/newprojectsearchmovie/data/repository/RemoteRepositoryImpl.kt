package com.dk.newprojectsearchmovie.data.repository

import com.dk.newprojectsearchmovie.data.RequestAPI
import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.domain.Repository
import retrofit2.Callback

class RemoteRepositoryImpl: Repository {
    override fun getMovieList(movieListType: MovieListType, callback: Callback<ImdbMovieList>) {
        return RequestAPI.create().getMovieList(movieListType.listType).enqueue(callback)
    }

    override fun getMovieDetail(movie: Movie, callback: Callback<ImdbMovieDetail>) {
        return RequestAPI.create().getMovieDetail(movie.id.toString()).enqueue(callback)
    }
}