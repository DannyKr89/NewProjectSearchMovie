package com.dk.newprojectsearchmovie.data.repository

import com.dk.newprojectsearchmovie.data.RequestAPI
import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.data.common.getMovieListType
import com.dk.newprojectsearchmovie.domain.Repository
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import retrofit2.Callback

class RemoteRepositoryImpl: Repository<Callback<ImdbMovieList>, Callback<ImdbMovieDetail>> {

    override fun getMovieList(movieListType: MovieListType, callbackList: Callback<ImdbMovieList>) {
        return RequestAPI.create().getMovieList(getMovieListType(movieListType)).enqueue(callbackList)
    }

    override fun getMovieDetail(movie: Movie, callbackDetail: Callback<ImdbMovieDetail>) {
        return RequestAPI.create().getMovieDetail(movie.id.toString()).enqueue(callbackDetail)
    }
}