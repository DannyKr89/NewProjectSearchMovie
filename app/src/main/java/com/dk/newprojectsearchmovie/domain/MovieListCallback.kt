package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList

interface MovieListCallback {
    fun onResponse(imdbMovieList: ImdbMovieList)
    fun onFailure()
}