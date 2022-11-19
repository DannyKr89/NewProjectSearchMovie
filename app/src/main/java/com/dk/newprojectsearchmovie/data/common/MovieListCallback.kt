package com.dk.newprojectsearchmovie.data.common

import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList

interface MovieListCallback {
    fun onResponse(imdbMovieList: ImdbMovieList)
    fun onFailure()
}