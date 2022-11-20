package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import retrofit2.Callback

class MovieInteractor(private val repository: Repository) {

    fun getList(movieListType: MovieListType, callback: Callback<ImdbMovieList>) {
            repository.getMovieList(movieListType, callback)
    }
}