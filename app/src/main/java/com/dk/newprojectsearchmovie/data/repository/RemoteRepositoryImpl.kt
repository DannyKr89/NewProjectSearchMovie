package com.dk.newprojectsearchmovie.data.repository

import com.dk.newprojectsearchmovie.data.RequestAPI
import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.domain.Repository
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie

class RemoteRepositoryImpl : Repository {
    private val requestAPI = RequestAPI.create()

    override fun getMovieList(movieListType: MovieListType): ImdbMovieList? {
        return requestAPI.getMovieList(movieListType.listType).execute().body()
    }

    override fun getMovieDetail(movie: Movie): ImdbMovieDetail? {
        return requestAPI.getMovieDetail(movie.id.toString()).execute().body()
    }
}